package service;

import dao.UsuarioDAO;
import model.Usuario;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UsuarioService {
    private UsuarioDAO usuarioDAO;
    // fallback in-memory
    private Map<String, Usuario> byEmail = new HashMap<>();
    private int seq = 1;

    public UsuarioService() {
        try {
            this.usuarioDAO = new UsuarioDAO();
        } catch (Throwable t) {
            this.usuarioDAO = null;
        }
    }

    public boolean cadastrarUsuario(String nome, String email, String senha, String tipo) {
        if (usuarioDAO != null) {
            try {
                if (usuarioDAO.buscarPorEmail(email) != null) return false;
                Usuario usuario = new Usuario(0, nome, email, senha, tipo);
                usuarioDAO.inserir(usuario);
                return true;
            } catch (SQLException | RuntimeException e) {
                // fall through to in-memory fallback
            }
        }
        if (byEmail.containsKey(email)) return false;
        Usuario u = new Usuario(seq++, nome, email, senha, tipo);
        byEmail.put(email, u);
        return true;
    }

    public Usuario autenticar(String email, String senha) {
        if (usuarioDAO != null) {
            try {
                Usuario usuario = usuarioDAO.buscarPorEmail(email);
                if (usuario != null && usuario.getSenha().equals(senha)) return usuario;
                return null;
            } catch (SQLException | RuntimeException e) {
                // fallback
            }
        }
        Usuario u = byEmail.get(email);
        if (u != null && u.getSenha().equals(senha)) return u;
        return null;
    }

    public boolean isAdmin(Usuario usuario) {
        return usuario != null && "ADMIN".equals(usuario.getTipo());
    }

    public boolean isProfessor(Usuario usuario) {
        return usuario != null && "PROFESSOR".equals(usuario.getTipo());
    }

    public boolean isAluno(Usuario usuario) {
        return usuario != null && "ALUNO".equals(usuario.getTipo());
    }
}