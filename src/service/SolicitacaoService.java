package service;

import dao.SolicitacaoDAO;
import dao.AuditoriaDAO;
import model.Solicitacao;
import model.Usuario;
import model.Auditoria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolicitacaoService {
    private SolicitacaoDAO solicitacaoDAO;
    private AuditoriaDAO auditoriaDAO;
    // fallback
    private Map<Integer, Solicitacao> map = new HashMap<>();
    private int seq = 1;

    public SolicitacaoService() {
        try {
            this.solicitacaoDAO = new SolicitacaoDAO();
            this.auditoriaDAO = new AuditoriaDAO();
        } catch (Throwable t) {
            this.solicitacaoDAO = null;
            this.auditoriaDAO = null;
        }
    }

    public boolean criarSolicitacao(Solicitacao s, Usuario usuario) {
        if (solicitacaoDAO != null) {
            try {
                List<Solicitacao> existentes = solicitacaoDAO.listarTodos();
                for (Solicitacao sol : existentes) {
                    boolean mesmoEspaco = sol.getEspacoFisicoId() == s.getEspacoFisicoId();
                    boolean conflito = !(s.getDataFim().isBefore(sol.getDataInicio()) || s.getDataInicio().isAfter(sol.getDataFim()));
                    if (mesmoEspaco && conflito && !"REJEITADA".equals(sol.getStatus())) return false;
                }
                s.setStatus("PENDENTE");
                s.setUsuarioId(usuario.getId());
                s.setDataCriacao(LocalDateTime.now());
                solicitacaoDAO.inserir(s);
                auditoriaDAO.inserir(new Auditoria(0, usuario.getId(), "CRIAR", "solicitacao", s.getId(), LocalDateTime.now(), "Solicitação criada"));
                return true;
            } catch (Exception e) {
                // fallback
            }
        }
        // in-memory fallback
        s.setId(seq++);
        s.setStatus("PENDENTE");
        s.setUsuarioId(usuario.getId());
        s.setDataCriacao(LocalDateTime.now());
        map.put(s.getId(), s);
        return true;
    }

    public List<Solicitacao> listarPorUsuario(int usuarioId) {
        if (solicitacaoDAO != null) {
            try { return solicitacaoDAO.listarPorUsuario(usuarioId); } catch (Exception e) { }
        }
        List<Solicitacao> res = new ArrayList<>();
        for (Solicitacao s : map.values()) if (s.getUsuarioId() == usuarioId) res.add(s);
        return res;
    }

    public List<Solicitacao> listarTodos() {
        if (solicitacaoDAO != null) {
            try { return solicitacaoDAO.listarTodos(); } catch (Exception e) { }
        }
        return new ArrayList<>(map.values());
    }

    public boolean aprovarSolicitacao(int id, Usuario admin, String justificativa) {
        if (!"ADMIN".equals(admin.getTipo())) throw new SecurityException("Apenas ADMIN pode aprovar/rejeitar solicitações.");
        if (solicitacaoDAO != null) {
            try {
                Solicitacao s = solicitacaoDAO.buscarPorId(id);
                if (s == null || !"PENDENTE".equals(s.getStatus())) return false;
                s.setStatus("APROVADA");
                s.setMotivo(justificativa);
                solicitacaoDAO.atualizar(s);
                auditoriaDAO.inserir(new Auditoria(0, admin.getId(), "APROVAR", "solicitacao", s.getId(), LocalDateTime.now(), justificativa));
                return true;
            } catch (Exception e) { }
        }
        Solicitacao s = map.get(id);
        if (s == null || !"PENDENTE".equals(s.getStatus())) return false;
        s.setStatus("APROVADA");
        s.setMotivo(justificativa);
        return true;
    }

    public boolean rejeitarSolicitacao(int id, Usuario admin, String justificativa) {
        if (!"ADMIN".equals(admin.getTipo())) throw new SecurityException("Apenas ADMIN pode aprovar/rejeitar solicitações.");
        if (solicitacaoDAO != null) {
            try {
                Solicitacao s = solicitacaoDAO.buscarPorId(id);
                if (s == null || !"PENDENTE".equals(s.getStatus())) return false;
                s.setStatus("REJEITADA");
                s.setMotivo(justificativa);
                solicitacaoDAO.atualizar(s);
                auditoriaDAO.inserir(new Auditoria(0, admin.getId(), "REJEITAR", "solicitacao", s.getId(), LocalDateTime.now(), justificativa));
                return true;
            } catch (Exception e) { }
        }
        Solicitacao s = map.get(id);
        if (s == null || !"PENDENTE".equals(s.getStatus())) return false;
        s.setStatus("REJEITADA");
        s.setMotivo(justificativa);
        return true;
    }
}