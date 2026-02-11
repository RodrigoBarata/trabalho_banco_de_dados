package dao;

import model.EspacoFisico;
import util.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspacoFisicoDAO {
    public void inserir(EspacoFisico espaco) throws SQLException {
        String sql = "INSERT INTO espaco_fisico (nome, descricao, capacidade, tipo, disponivel) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, espaco.getNome());
            stmt.setString(2, espaco.getDescricao());
            stmt.setInt(3, espaco.getCapacidade());
            stmt.setString(4, espaco.getTipo());
            stmt.setBoolean(5, espaco.isDisponivel());
            stmt.executeUpdate();
        }
    }

    public EspacoFisico buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM espaco_fisico WHERE id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearEspaco(rs);
                }
            }
        }
        return null;
    }

    public List<EspacoFisico> listarTodos() throws SQLException {
        List<EspacoFisico> lista = new ArrayList<>();
        String sql = "SELECT * FROM espaco_fisico";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearEspaco(rs));
            }
        }
        return lista;
    }

    public void atualizar(EspacoFisico espaco) throws SQLException {
        String sql = "UPDATE espaco_fisico SET nome=?, descricao=?, capacidade=?, tipo=?, disponivel=? WHERE id=?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, espaco.getNome());
            stmt.setString(2, espaco.getDescricao());
            stmt.setInt(3, espaco.getCapacidade());
            stmt.setString(4, espaco.getTipo());
            stmt.setBoolean(5, espaco.isDisponivel());
            stmt.setInt(6, espaco.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM espaco_fisico WHERE id=?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private EspacoFisico mapearEspaco(ResultSet rs) throws SQLException {
        EspacoFisico e = new EspacoFisico();
        e.setId(rs.getInt("id"));
        e.setNome(rs.getString("nome"));
        e.setDescricao(rs.getString("descricao"));
        e.setCapacidade(rs.getInt("capacidade"));
        e.setTipo(rs.getString("tipo"));
        e.setDisponivel(rs.getBoolean("disponivel"));
        return e;
    }
} 