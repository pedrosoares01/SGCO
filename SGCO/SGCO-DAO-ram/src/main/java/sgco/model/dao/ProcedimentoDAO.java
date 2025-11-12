package sgco.model.dao;

import sgco.dao.exception.NomeProcedimentoInvalidoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import sgco.model.domain.Procedimento;

public class ProcedimentoDAO {

    public boolean inserir(Procedimento p) throws NomeProcedimentoInvalidoException {
        validarNomeProcedimento(p.getNome());

        String sql = "INSERT INTO procedimento (nome, preco) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro SQL ao inserir procedimento: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Erro de conexão: " + e.getMessage());
            return false;
        }
    }

    public List<Procedimento> pesquisarPorNome(String nome) {
        List<Procedimento> procedimentos = new ArrayList<>();
        String sql = "SELECT id, nome, preco FROM procedimento WHERE nome LIKE ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nomeProcedimento = rs.getString("nome");
                    double preco = rs.getDouble("preco");
                    procedimentos.add(new Procedimento(id, nomeProcedimento, preco));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro SQL ao pesquisar procedimentos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
        }

        return procedimentos;
    }

    public Procedimento buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM procedimento WHERE id = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Procedimento p = new Procedimento();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                return p;
            }

        } catch (SQLException e) {
            System.err.println("Erro SQL ao buscar procedimento por ID: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro geral ao buscar procedimento: " + e.getMessage());
            throw e;
        }

        return null;
    }

    public boolean atualizar(Procedimento p) throws Exception {
        String sql = "UPDATE procedimento SET nome = ?, preco = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar procedimento: " + e.getMessage());
            return false;
        }
    }

    private void validarNomeProcedimento(String nome) throws NomeProcedimentoInvalidoException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new NomeProcedimentoInvalidoException("O nome do procedimento não pode estar vazio.");
        }

        if (!nome.matches("^[A-Za-zÀ-ÿ\\s]+$")) {
            throw new NomeProcedimentoInvalidoException("O nome do procedimento deve conter apenas letras e espaços.");
        }

        if (nome.length() < 3) {
            throw new NomeProcedimentoInvalidoException("O nome do procedimento deve ter pelo menos 3 caracteres.");
        }
    }
    
    public boolean excluir(int id) throws Exception {
        String sql = "DELETE FROM procedimento WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir procedimento: " + e.getMessage());
            return false;
        }
    }
    
}
