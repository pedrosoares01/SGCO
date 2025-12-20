package sgco.model.dao;

import sgco.sgco.domain.FornecedorMaterial;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorMaterialDAO {

    // Inserir novo fornecedor
    public boolean inserir(FornecedorMaterial fornecedor) throws SQLException {
        String sql = "INSERT INTO fornecedor_material (nome, contato, email) VALUES (?, ?, ?)";
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getContato());
            stmt.setString(3, fornecedor.getEmail());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry") || e.getErrorCode() == 1062) {
                throw new SQLException("Já existe um fornecedor com este nome.", e);
            }
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Buscar fornecedor por nome
    public FornecedorMaterial buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM fornecedor_material WHERE nome = ?";
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new FornecedorMaterial(
                            rs.getString("nome"),
                            rs.getString("contato"),
                            rs.getString("email")
                    );
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Atualizar fornecedor
    public boolean atualizar(String nomeOriginal, FornecedorMaterial fornecedor) throws SQLException {
        String sql = "UPDATE fornecedor_material SET nome = ?, contato = ?, email = ? WHERE nome = ?";
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getContato());
            stmt.setString(3, fornecedor.getEmail());
            stmt.setString(4, nomeOriginal);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry") || e.getErrorCode() == 1062) {
                throw new SQLException("Já existe um fornecedor com este nome.", e);
            }
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Excluir fornecedor
    public boolean excluir(String nome) throws SQLException {
        String sql = "DELETE FROM fornecedor_material WHERE nome = ?";
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Pesquisar fornecedores por nome
    public List<FornecedorMaterial> pesquisarPorNome(String nome) throws SQLException {
        List<FornecedorMaterial> resultados = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor_material WHERE nome LIKE ? ORDER BY nome";
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    FornecedorMaterial fm = new FornecedorMaterial(
                            rs.getString("nome"),
                            rs.getString("contato"),
                            rs.getString("email")
                    );
                    resultados.add(fm);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultados;
    }

    // Listar todos os fornecedores
    public List<FornecedorMaterial> listarTodos() throws SQLException {
        List<FornecedorMaterial> todos = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor_material ORDER BY nome";
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FornecedorMaterial fm = new FornecedorMaterial(
                        rs.getString("nome"),
                        rs.getString("contato"),
                        rs.getString("email")
                );
                todos.add(fm);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return todos;
    }
}