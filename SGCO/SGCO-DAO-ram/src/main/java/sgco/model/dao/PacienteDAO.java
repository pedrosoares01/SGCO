package sgco.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import sgco.model.domain.Paciente;

public class PacienteDAO {

    public boolean inserir(Paciente p) {
        String sql = "INSERT INTO paciente (nome, cpf, endereco, telefone, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getEndereco());
            stmt.setString(4, p.getTelefone());
            stmt.setString(5, p.getEmail());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erro SQL ao inserir paciente: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Erro de conexão ao inserir paciente: " + e.getMessage());
            return false;
        }
    }

    public List<Paciente> pesquisarPorNome(String nome) {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT id, nome, cpf, endereco, telefone, email FROM paciente WHERE nome LIKE ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pacientes.add(new Paciente(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("endereco"),
                            rs.getString("telefone"),
                            rs.getString("email")
                    ));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro SQL ao pesquisar pacientes: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro de conexão ao pesquisar pacientes: " + e.getMessage());
        }

        return pacientes;
    }

    public Paciente buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM paciente WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Paciente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("email")
                );
            }

        } catch (SQLException e) {
            System.err.println("Erro SQL ao buscar paciente por ID: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro ao buscar paciente: " + e.getMessage());
            throw e;
        }

        return null;
    }

    public boolean atualizar(Paciente p) throws Exception {
        String sql = "UPDATE paciente SET nome = ?, cpf = ?, endereco = ?, telefone = ?, email = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getEndereco());
            stmt.setString(4, p.getTelefone());
            stmt.setString(5, p.getEmail());
            stmt.setInt(6, p.getId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar paciente: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) throws Exception {
        String sql = "DELETE FROM paciente WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir paciente: " + e.getMessage());
            return false;
        }
    }
}
