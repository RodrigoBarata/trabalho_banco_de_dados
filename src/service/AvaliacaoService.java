package service;

import dao.AvaliacaoDAO;
import model.Avaliacao;

import java.sql.SQLException;
import java.util.List;

public class AvaliacaoService {
    private AvaliacaoDAO avaliacaoDAO;

    public AvaliacaoService() {
        this.avaliacaoDAO = new AvaliacaoDAO();
    }

    public void criarAvaliacao(Avaliacao a) throws SQLException {
        avaliacaoDAO.inserir(a);
    }

    public Avaliacao buscarPorId(int id) throws SQLException {
        return avaliacaoDAO.buscarPorId(id);
    }

    public List<Avaliacao> listarPorEspaco(int espacoId) throws SQLException {
        return avaliacaoDAO.listarPorEspaco(espacoId);
    }

    public List<Avaliacao> listarTodos() throws SQLException {
        return avaliacaoDAO.listarTodos();
    }

    public void atualizarAvaliacao(Avaliacao a) throws SQLException {
        avaliacaoDAO.atualizar(a);
    }

    public void deletarAvaliacao(int id) throws SQLException {
        avaliacaoDAO.deletar(id);
    }
} 