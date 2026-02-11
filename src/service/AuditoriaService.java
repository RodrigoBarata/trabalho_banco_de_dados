package service;

import dao.AuditoriaDAO;
import model.Auditoria;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AuditoriaService {
    private AuditoriaDAO auditoriaDAO;
    // fallback
    private List<Auditoria> list = new CopyOnWriteArrayList<>();

    public AuditoriaService() {
        try {
            this.auditoriaDAO = new AuditoriaDAO();
        } catch (Throwable t) {
            this.auditoriaDAO = null;
        }
    }

    public List<Auditoria> listarTodos() {
        if (auditoriaDAO != null) {
            try { return auditoriaDAO.listarTodos(); } catch (Exception e) { }
        }
        return new ArrayList<>(list);
    }

    public List<Auditoria> listarPorUsuario(int usuarioId) {
        if (auditoriaDAO != null) {
            try { return auditoriaDAO.listarPorUsuario(usuarioId); } catch (Exception e) { }
        }
        List<Auditoria> out = new ArrayList<>();
        for (Auditoria a : list) if (a.getUsuarioId() == usuarioId) out.add(a);
        return out;
    }

    public void registrar(Auditoria a) {
        if (auditoriaDAO != null) {
            try { auditoriaDAO.inserir(a); return; } catch (Exception e) { }
        }
        list.add(a);
    }
}