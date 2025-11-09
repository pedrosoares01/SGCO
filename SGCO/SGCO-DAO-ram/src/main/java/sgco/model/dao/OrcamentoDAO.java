package sgco.model.dao;

import sgco.model.domain.Orcamento;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrcamentoDAO {

    private static final Logger logger = Logger.getLogger(OrcamentoDAO.class.getName());

    public void inserir(Orcamento orcamento) {
        String sql = "INSERT INTO orcamento (descricao, valor, id_profissional, id_paciente) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, orcamento.getDescricao());
            stmt.setDouble(2, orcamento.getValor());
            stmt.setInt(3, orcamento.getIdProfissional());
            stmt.setInt(4, orcamento.getIdPaciente());

            stmt.executeUpdate();
            logger.info("Orçamento inserido com sucesso!");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro SQL ao inserir orçamento: {0}", e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro de conexão ao inserir orçamento: {0}", e.getMessage());
        }
    }

    public Orcamento buscarPorId(int id) {
        String sql = "SELECT id, descricao, valor, id_profissional, id_paciente FROM orcamento WHERE id = ?";
        Orcamento o = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    o = new Orcamento();
                    o.setId(rs.getInt("id"));
                    o.setDescricao(rs.getString("descricao"));
                    o.setValor(rs.getDouble("valor"));
                    o.setIdProfissional(rs.getInt("id_profissional"));
                    o.setIdPaciente(rs.getInt("id_paciente"));
                }
            }

            if (o == null) {
                logger.log(Level.INFO, "Nenhum orçamento encontrado com o ID: {0}", id);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro SQL ao buscar orçamento por ID: {0}", e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro de conexão ao buscar orçamento: {0}", e.getMessage());
        }

        return o;
    }

    public List<Orcamento> listarTodos() {
        List<Orcamento> lista = new ArrayList<>();
        String sql = "SELECT id, descricao, valor, id_profissional, id_paciente FROM orcamento";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Orcamento o = new Orcamento();
                o.setId(rs.getInt("id"));
                o.setDescricao(rs.getString("descricao"));
                o.setValor(rs.getDouble("valor"));
                o.setIdProfissional(rs.getInt("id_profissional"));
                o.setIdPaciente(rs.getInt("id_paciente"));
                lista.add(o);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro SQL ao listar orçamentos: {0}", e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro de conexão ao listar orçamentos: {0}", e.getMessage());
        }

        return lista;
    }

    public void excluir(int id) {
        String sql = "DELETE FROM orcamento WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            logger.info("Orçamento excluído com sucesso!");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro SQL ao excluir orçamento: {0}", e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro de conexão ao excluir orçamento: {0}", e.getMessage());
        }
    }
}