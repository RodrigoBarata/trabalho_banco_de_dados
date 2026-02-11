package dao;

import model.Avaliacao;
import util.ConexaoBD;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDAO {
    public void inserir(Avaliacao a) throws SQLException {
        String sql = "INSERT INTO avaliacao (usuario_id, espaco_fisico_id, nota, comentario, data_avaliacao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, a.getUsuarioId());
            stmt.setInt(2, a.getEspacoFisicoId());
            stmt.setInt(3, a.getNota());
            stmt.setString(4, a.getComentario());
            stmt.setTimestamp(5, Timestamp.valueOf(a.getDataAvaliacao()));
            stmt.executeUpdate();
        }
    }

    public Avaliacao buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM avaliacao WHERE id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearAvaliacao(rs);
                }
            }
        }
        return null;
    }

    public List<Avaliacao> listarPorEspaco(int espacoId) throws SQLException {
        List<Avaliacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM avaliacao WHERE espaco_fisico_id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, espacoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearAvaliacao(rs));
                }
            }
        }
        return lista;
    }

    public List<Avaliacao> listarTodos() throws SQLException {
        List<Avaliacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM avaliacao";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearAvaliacao(rs));
            }
        }
        return lista;
    }

    public void atualizar(Avaliacao a) throws SQLException {
        String sql = "UPDATE avaliacao SET usuario_id=?, espaco_fisico_id=?, nota=?, comentario=?, data_avaliacao=? WHERE id=?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, a.getUsuarioId());
            stmt.setInt(2, a.getEspacoFisicoId());
            stmt.setInt(3, a.getNota());
            stmt.setString(4, a.getComentario());
            stmt.setTimestamp(5, Timestamp.valueOf(a.getDataAvaliacao()));
            stmt.setInt(6, a.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM avaliacao WHERE id=?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Avaliacao mapearAvaliacao(ResultSet rs) throws SQLException {
        Avaliacao a = new Avaliacao();
        a.setId(rs.getInt("id"));
        a.setUsuarioId(rs.getInt("usuario_id"));
        a.setEspacoFisicoId(rs.getInt("espaco_fisico_id"));
        a.setNota(rs.getInt("nota"));
        a.setComentario(rs.getString("comentario"));
        a.setDataAvaliacao(rs.getTimestamp("data_avaliacao").toLocalDateTime());
        return a;
    }
} 