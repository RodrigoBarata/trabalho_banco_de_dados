package dao;

import model.Equipamento;
import util.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO {
    public void inserir(Equipamento eq) throws SQLException {
        String sql = "INSERT INTO equipamento (nome, descricao, tipo, disponivel, espaco_fisico_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eq.getNome());
            stmt.setString(2, eq.getDescricao());
            stmt.setString(3, eq.getTipo());
            stmt.setBoolean(4, eq.isDisponivel());
            if (eq.getEspacoFisicoId() != null) {
                stmt.setInt(5, eq.getEspacoFisicoId());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            stmt.executeUpdate();
        }
    }

    public Equipamento buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM equipamento WHERE id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearEquipamento(rs);
                }
            }
        }
        return null;
    }

    public List<Equipamento> listarTodos() throws SQLException {
        List<Equipamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM equipamento";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearEquipamento(rs));
            }
        }
        return lista;
    }

    public void atualizar(Equipamento eq) throws SQLException {
        String sql = "UPDATE equipamento SET nome=?, descricao=?, tipo=?, disponivel=?, espaco_fisico_id=? WHERE id=?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eq.getNome());
            stmt.setString(2, eq.getDescricao());
            stmt.setString(3, eq.getTipo());
            stmt.setBoolean(4, eq.isDisponivel());
            if (eq.getEspacoFisicoId() != null) {
                stmt.setInt(5, eq.getEspacoFisicoId());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            stmt.setInt(6, eq.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM equipamento WHERE id=?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Equipamento mapearEquipamento(ResultSet rs) throws SQLException {
        Equipamento e = new Equipamento();
        e.setId(rs.getInt("id"));
        e.setNome(rs.getString("nome"));
        e.setDescricao(rs.getString("descricao"));
        e.setTipo(rs.getString("tipo"));
        e.setDisponivel(rs.getBoolean("disponivel"));
        int espacoId = rs.getInt("espaco_fisico_id");
        if (rs.wasNull()) {
            e.setEspacoFisicoId(null);
        } else {
            e.setEspacoFisicoId(espacoId);
        }
        return e;
    }
} 