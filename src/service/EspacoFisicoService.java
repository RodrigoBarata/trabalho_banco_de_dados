package service;

import dao.EspacoFisicoDAO;
import model.EspacoFisico;
import model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EspacoFisicoService {
    private EspacoFisicoDAO espacoFisicoDAO;
    // fallback in-memory
    private Map<Integer, EspacoFisico> map = new HashMap<>();
    private int seq = 1;

    public EspacoFisicoService() {
        try {
            this.espacoFisicoDAO = new EspacoFisicoDAO();
        } catch (Throwable t) {
            this.espacoFisicoDAO = null;
        }
    }

    public void criarEspaco(EspacoFisico espaco, Usuario usuario) {
        if (!"ADMIN".equals(usuario.getTipo())) throw new SecurityException("Apenas ADMIN pode criar espaços físicos.");
        if (espacoFisicoDAO != null) {
            try {
                espacoFisicoDAO.inserir(espaco);
                return;
            } catch (SQLException | RuntimeException e) {
                // fallback
            }
        }
        espaco.setId(seq++);
        map.put(espaco.getId(), espaco);
    }

    public EspacoFisico buscarPorId(int id) {
        if (espacoFisicoDAO != null) {
            try { return espacoFisicoDAO.buscarPorId(id); } catch (SQLException | RuntimeException e) { }
        }
        return map.get(id);
    }

    public List<EspacoFisico> listarTodos() {
        if (espacoFisicoDAO != null) {
            try { return espacoFisicoDAO.listarTodos(); } catch (SQLException | RuntimeException e) { }
        }
        return new ArrayList<>(map.values());
    }

    public void atualizarEspaco(EspacoFisico espaco, Usuario usuario) {
        if (!"ADMIN".equals(usuario.getTipo())) throw new SecurityException("Apenas ADMIN pode atualizar espaços físicos.");
        if (espacoFisicoDAO != null) {
            try { espacoFisicoDAO.atualizar(espaco); return; } catch (SQLException | RuntimeException e) { }
        }
        map.put(espaco.getId(), espaco);
    }

    public void deletarEspaco(int id, Usuario usuario) {
        if (!"ADMIN".equals(usuario.getTipo())) throw new SecurityException("Apenas ADMIN pode deletar espaços físicos.");
        if (espacoFisicoDAO != null) {
            try { espacoFisicoDAO.deletar(id); return; } catch (SQLException | RuntimeException e) { }
        }
        map.remove(id);
    }
}