package dao;

import model.Solicitacao;
import util.ConexaoBD;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoDAO {
    public void inserir(Solicitacao s) throws SQLException {
        String sql = "INSERT INTO solicitacao (usuario_id, espaco_fisico_id, data_inicio, data_fim, status, motivo, data_criacao) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, s.getUsuarioId());
            stmt.setInt(2, s.getEspacoFisicoId());
            stmt.setTimestamp(3, Timestamp.valueOf(s.getDataInicio()));
            stmt.setTimestamp(4, Timestamp.valueOf(s.getDataFim()));
            stmt.setString(5, s.getStatus());
            stmt.setString(6, s.getMotivo());
            stmt.setTimestamp(7, Timestamp.valueOf(s.getDataCriacao()));
            stmt.executeUpdate();
        }
    }

    public Solicitacao buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM solicitacao WHERE id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearSolicitacao(rs);
                }
            }
        }
        return null;
    }

    public List<Solicitacao> listarPorUsuario(int usuarioId) throws SQLException {
        List<Solicitacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitacao WHERE usuario_id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearSolicitacao(rs));
                }
            }
        }
        return lista;
    }

    public List<Solicitacao> listarTodos() throws SQLException {
        List<Solicitacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitacao";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearSolicitacao(rs));
            }
        }
        return lista;
    }

    public void atualizar(Solicitacao s) throws SQLException {
        String sql = "UPDATE solicitacao SET usuario_id=?, espaco_fisico_id=?, data_inicio=?, data_fim=?, status=?, motivo=?, data_criacao=? WHERE id=?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, s.getUsuarioId());
            stmt.setInt(2, s.getEspacoFisicoId());
            stmt.setTimestamp(3, Timestamp.valueOf(s.getDataInicio()));
            stmt.setTimestamp(4, Timestamp.valueOf(s.getDataFim()));
            stmt.setString(5, s.getStatus());
            stmt.setString(6, s.getMotivo());
            stmt.setTimestamp(7, Timestamp.valueOf(s.getDataCriacao()));
            stmt.setInt(8, s.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM solicitacao WHERE id=?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Solicitacao mapearSolicitacao(ResultSet rs) throws SQLException {
        Solicitacao s = new Solicitacao();
        s.setId(rs.getInt("id"));
        s.setUsuarioId(rs.getInt("usuario_id"));
        s.setEspacoFisicoId(rs.getInt("espaco_fisico_id"));
        s.setDataInicio(rs.getTimestamp("data_inicio").toLocalDateTime());
        s.setDataFim(rs.getTimestamp("data_fim").toLocalDateTime());
        s.setStatus(rs.getString("status"));
        s.setMotivo(rs.getString("motivo"));
        s.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
        return s;
    }
} 