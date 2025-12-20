package sgco.model.dao;

import sgco.sgco.domain.Material;
import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {

    public boolean inserir(Material material) throws SQLException {
        String sql = "INSERT INTO material (nome, quantidade) VALUES (?, ?)";
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String nome = material.getNome();
            if (nome != null && nome.length() > 255) {
                nome = nome.substring(0, 255);
            }

            stmt.setString(1, nome);
            stmt.setInt(2, material.getQuantidade());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry") || e.getErrorCode() == 1062) {
                throw new SQLException("Já existe um material com este nome.", e);
            }
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean excluir(String nome) throws SQLException {
        String sql = "DELETE FROM material WHERE nome = ?";
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Material> pesquisarPorNome(String nome) throws SQLException {
        List<Material> materiais = new ArrayList<>();
        String sql = "SELECT * FROM material WHERE nome LIKE ? ORDER BY nome";
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + (nome != null ? nome : "") + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Material material = new Material(
                            rs.getString("nome"),
                            rs.getInt("quantidade")
                    );
                    materiais.add(material);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return materiais;
    }

    public List<Material> listarTodos() throws SQLException {
        List<Material> materiais = new ArrayList<>();
        String sql = "SELECT * FROM material ORDER BY nome";
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Material material = new Material(
                        rs.getString("nome"),
                        rs.getInt("quantidade")
                );
                materiais.add(material);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return materiais;
    }
}