package sgco.model.dao;

import sgco.sgco.domain.FornecedorServico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorServicoDAO {

    public boolean inserir(FornecedorServico fornecedorServico) throws SQLException {
        String sql = "INSERT INTO fornecedor_servico (nome_servico, fornecedor, contato) VALUES (?, ?, ?)";
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String nomeServico = fornecedorServico.getNomeServico();
            if (nomeServico != null && nomeServico.length() > 255) {
                nomeServico = nomeServico.substring(0, 255);
            }

            String fornecedor = fornecedorServico.getFornecedor();
            if (fornecedor != null && fornecedor.length() > 255) {
                fornecedor = fornecedor.substring(0, 255);
            }

            String contato = fornecedorServico.getContato();
            if (contato != null && contato.length() > 100) {
                contato = contato.substring(0, 100);
            }

            stmt.setString(1, nomeServico);
            stmt.setString(2, fornecedor);
            stmt.setString(3, contato);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry") || e.getErrorCode() == 1062) {
                throw new SQLException("Já existe um serviço com este nome e fornecedor.", e);
            }
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Buscar por nome do serviço e fornecedor (para edição)
    public FornecedorServico buscarPorChave(String nomeServico, String fornecedor) throws SQLException {
        String sql = "SELECT * FROM fornecedor_servico WHERE nome_servico = ? AND fornecedor = ?";
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeServico);
            stmt.setString(2, fornecedor);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new FornecedorServico(
                            rs.getString("nome_servico"),
                            rs.getString("fornecedor"),
                            rs.getString("contato")
                    );
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Atualizar serviço (baseado no nome e fornecedor original)
    public boolean atualizar(String nomeServicoOriginal, String fornecedorOriginal,
                             FornecedorServico fornecedorServico) throws SQLException {
        String sql = "UPDATE fornecedor_servico SET nome_servico = ?, fornecedor = ?, contato = ? " +
                "WHERE nome_servico = ? AND fornecedor = ?";
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String novoNomeServico = fornecedorServico.getNomeServico();
            if (novoNomeServico != null && novoNomeServico.length() > 255) {
                novoNomeServico = novoNomeServico.substring(0, 255);
            }

            String novoFornecedor = fornecedorServico.getFornecedor();
            if (novoFornecedor != null && novoFornecedor.length() > 255) {
                novoFornecedor = novoFornecedor.substring(0, 255);
            }

            String contato = fornecedorServico.getContato();
            if (contato != null && contato.length() > 100) {
                contato = contato.substring(0, 100);
            }

            stmt.setString(1, novoNomeServico);
            stmt.setString(2, novoFornecedor);
            stmt.setString(3, contato);
            stmt.setString(4, nomeServicoOriginal);
            stmt.setString(5, fornecedorOriginal);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry") || e.getErrorCode() == 1062) {
                throw new SQLException("Já existe um serviço com este nome e fornecedor.", e);
            }
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Excluir serviço
    public boolean excluir(String nomeServico, String fornecedor) throws SQLException {
        String sql = "DELETE FROM fornecedor_servico WHERE nome_servico = ? AND fornecedor = ?";
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeServico);
            stmt.setString(2, fornecedor);

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Pesquisar serviços
    public List<FornecedorServico> pesquisar(String nomeServico, String fornecedor) throws SQLException {
        List<FornecedorServico> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM fornecedor_servico WHERE 1=1");

        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection conn = connectionFactory.getConnection()) {
            if (nomeServico != null && !nomeServico.isEmpty()) {
                sql.append(" AND nome_servico LIKE ?");
            }
            if (fornecedor != null && !fornecedor.isEmpty()) {
                sql.append(" AND fornecedor LIKE ?");
            }
            sql.append(" ORDER BY nome_servico, fornecedor");

            try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
                int paramIndex = 1;
                if (nomeServico != null && !nomeServico.isEmpty()) {
                    stmt.setString(paramIndex++, "%" + nomeServico + "%");
                }
                if (fornecedor != null && !fornecedor.isEmpty()) {
                    stmt.setString(paramIndex++, "%" + fornecedor + "%");
                }

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        FornecedorServico fs = new FornecedorServico(
                                rs.getString("nome_servico"),
                                rs.getString("fornecedor"),
                                rs.getString("contato")
                        );
                        resultados.add(fs);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultados;
    }

    // Listar todos os serviços
    public List<FornecedorServico> listarTodos() throws SQLException {
        List<FornecedorServico> todos = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor_servico ORDER BY nome_servico, fornecedor";
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FornecedorServico fs = new FornecedorServico(
                        rs.getString("nome_servico"),
                        rs.getString("fornecedor"),
                        rs.getString("contato")
                );
                todos.add(fs);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return todos;
    }
}