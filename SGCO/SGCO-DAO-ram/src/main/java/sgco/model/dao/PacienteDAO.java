package sgco.model.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgco.model.domain.Paciente;

public class PacienteDAO {

    private static final Logger logger = Logger.getLogger(PacienteDAO.class.getName());

    public List<Paciente> pesquisarPorNome(String nome) {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT id, nome, cpf, endereco, telefone, email FROM paciente WHERE nome LIKE ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Paciente p = new Paciente();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setCpf(rs.getString("cpf"));
                    p.setEndereco(rs.getString("endereco"));
                    p.setTelefone(rs.getString("telefone"));
                    p.setEmail(rs.getString("email"));
                    pacientes.add(p);
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro SQL ao pesquisar pacientes: {0}", e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro de conexão ao pesquisar pacientes: {0}", e.getMessage());
        }

        return pacientes;
    }

    public Paciente buscarPorId(int id) {
        String sql = "SELECT id, nome, cpf, endereco, telefone, email FROM paciente WHERE id = ?";
        Paciente p = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p = new Paciente();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setCpf(rs.getString("cpf"));
                    p.setEndereco(rs.getString("endereco"));
                    p.setTelefone(rs.getString("telefone"));
                    p.setEmail(rs.getString("email"));
                }
            }

            if (p == null) {
                logger.log(Level.INFO, "Nenhum paciente encontrado com o ID: {0}", id);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro SQL ao buscar paciente por ID: {0}", e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro de conexão ao buscar paciente por ID: {0}", e.getMessage());
        }

        return p;
    }
}

