package sgco.model.dao;
import sgco.sgco.domain.FornecedorMaterial;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorMaterialDAO {
    //assim que faz a conexao
    // lembra de colocar catch no final dos try
    public boolean inserir(FornecedorMaterial fornecedor) throws SQLException {
        String sql = "INSERT INTO fornecedor_material (nome, contato, email) VALUES (?, ?, ?)";
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            String nome = fornecedor.getNome();
            if (nome != null && nome.length() > 255) {
                nome = nome.substring(0, 255);
            }

            String contato = fornecedor.getContato();
            if (contato != null && contato.length() > 20) {
                contato = contato.substring(0, 20);
            }

            String email = fornecedor.getEmail();
            if (email != null && email.length() > 100) {
                email = email.substring(0, 100);
            }

            stmt.setString(1, nome);
            stmt.setString(2, contato);
            stmt.setString(3, email);

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

    public boolean excluir(String nome) throws SQLException {
        String sql = "DELETE FROM fornecedor_material WHERE nome = ?";
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            return stmt.executeUpdate() > 0;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<FornecedorMaterial> pesquisarPorNome(String nome) throws SQLException {
        List<FornecedorMaterial> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor_material WHERE nome LIKE ? ORDER BY nome";
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + (nome != null ? nome : "") + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    FornecedorMaterial fornecedor = new FornecedorMaterial(
                            rs.getString("nome"),
                            rs.getString("contato"),
                            rs.getString("email")
                    );
                    fornecedores.add(fornecedor);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fornecedores;
    }

    public List<FornecedorMaterial> listarTodos() throws SQLException {
        List<FornecedorMaterial> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor_material ORDER BY nome";
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FornecedorMaterial fornecedor = new FornecedorMaterial(
                        rs.getString("nome"),
                        rs.getString("contato"),
                        rs.getString("email")
                );
                fornecedores.add(fornecedor);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fornecedores;
    }
}