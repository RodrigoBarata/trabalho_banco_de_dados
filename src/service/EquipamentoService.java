package service;

import dao.EquipamentoDAO;
import model.Equipamento;
import model.Usuario;

import java.sql.SQLException;
import java.util.List;

public class EquipamentoService {
    private EquipamentoDAO equipamentoDAO;

    public EquipamentoService() {
        this.equipamentoDAO = new EquipamentoDAO();
    }

    public void criarEquipamento(Equipamento eq, Usuario usuario) throws SQLException {
        if (!"ADMIN".equals(usuario.getTipo())) {
            throw new SecurityException("Apenas ADMIN pode criar equipamentos.");
        }
        equipamentoDAO.inserir(eq);
    }

    public Equipamento buscarPorId(int id) throws SQLException {
        return equipamentoDAO.buscarPorId(id);
    }

    public List<Equipamento> listarTodos() throws SQLException {
        return equipamentoDAO.listarTodos();
    }

    public void atualizarEquipamento(Equipamento eq, Usuario usuario) throws SQLException {
        if (!"ADMIN".equals(usuario.getTipo())) {
            throw new SecurityException("Apenas ADMIN pode atualizar equipamentos.");
        }
        equipamentoDAO.atualizar(eq);
    }

    public void deletarEquipamento(int id, Usuario usuario) throws SQLException {
        if (!"ADMIN".equals(usuario.getTipo())) {
            throw new SecurityException("Apenas ADMIN pode deletar equipamentos.");
        }
        equipamentoDAO.deletar(id);
    }
} 