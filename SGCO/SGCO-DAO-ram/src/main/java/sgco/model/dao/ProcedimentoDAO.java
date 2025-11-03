package sgco.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sgco.model.domain.Procedimento;

public class ProcedimentoDAO {

    public boolean inserir(Procedimento p) {
        String sql = "INSERT INTO procedimento (nome, preco) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            int rowsAffected = stmt.executeUpdate();

            System.out.println("Rows affected: " + rowsAffected);
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro SQL ao inserir procedimento: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
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
                    procedimentos.add(new Procedimento(nomeProcedimento, preco));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro SQL ao pesquisar procedimentos: " + e.getMessage());
        } catch (Exception e) { 
            System.err.println("Erro ao conectar: " + e.getMessage());
        }

        return procedimentos;
    }
}
