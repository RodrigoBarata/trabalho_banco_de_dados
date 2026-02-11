package dao;

import model.Auditoria;
import util.ConexaoBD;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuditoriaDAO {
    public void inserir(Auditoria a) throws SQLException {
        String sql = "INSERT INTO auditoria (usuario_id, acao, tabela, registro_id, data_acao, detalhes) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, a.getUsuarioId());
            stmt.setString(2, a.getAcao());
            stmt.setString(3, a.getTabela());
            stmt.setInt(4, a.getRegistroId());
            stmt.setTimestamp(5, Timestamp.valueOf(a.getDataAcao()));
            stmt.setString(6, a.getDetalhes());
            stmt.executeUpdate();
        }
    }

    public List<Auditoria> listarTodos() throws SQLException {
        List<Auditoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM auditoria ORDER BY data_acao DESC";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearAuditoria(rs));
            }
        }
        return lista;
    }

    public List<Auditoria> listarPorUsuario(int usuarioId) throws SQLException {
        List<Auditoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM auditoria WHERE usuario_id = ? ORDER BY data_acao DESC";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearAuditoria(rs));
                }
            }
        }
        return lista;
    }

    private Auditoria mapearAuditoria(ResultSet rs) throws SQLException {
        Auditoria a = new Auditoria();
        a.setId(rs.getInt("id"));
        a.setUsuarioId(rs.getInt("usuario_id"));
        a.setAcao(rs.getString("acao"));
        a.setTabela(rs.getString("tabela"));
        a.setRegistroId(rs.getInt("registro_id"));
        a.setDataAcao(rs.getTimestamp("data_acao").toLocalDateTime());
        a.setDetalhes(rs.getString("detalhes"));
        return a;
    }
} 