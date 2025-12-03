package sgco.model.dao;

import sgco.model.domain.Orcamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrcamentoDAO {

    public boolean inserir(Orcamento o) {
        String sql = "INSERT INTO orcamento (descricao, valor, id_profissional, id_paciente, id_procedimentos) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, o.getDescricao());
            stmt.setDouble(2, o.getValor());
            stmt.setInt(3, o.getIdProfissional());
            stmt.setInt(4, o.getIdPaciente());

            String idsProcedimentosStr = String.join(",", o.getIdsProcedimentos().stream().map(String::valueOf).toList());
            stmt.setString(5, idsProcedimentosStr);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erro SQL ao inserir orçamento: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Erro de conexão ao inserir orçamento: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizar(Orcamento o) {
        String sql = "UPDATE orcamento SET descricao = ?, valor = ?, id_profissional = ?, id_paciente = ?, id_procedimentos = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, o.getDescricao());
            stmt.setDouble(2, o.getValor());
            stmt.setInt(3, o.getIdProfissional());
            stmt.setInt(4, o.getIdPaciente());

            String idsProcedimentosStr = String.join(",", o.getIdsProcedimentos().stream().map(String::valueOf).toList());
            stmt.setString(5, idsProcedimentosStr);

            stmt.setInt(6, o.getId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erro SQL ao atualizar orçamento: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Erro de conexão ao atualizar orçamento: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM orcamento WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erro SQL ao excluir orçamento: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Erro de conexão ao excluir orçamento: " + e.getMessage());
            return false;
        }
    }

    public Orcamento buscarPorId(int id) {
        String sql = """
            SELECT o.*, p.nome AS nome_paciente, pr.nome AS nome_profissional
            FROM orcamento o
            LEFT JOIN paciente p ON o.id_paciente = p.id
            LEFT JOIN profissional pr ON o.id_profissional = pr.id_profissional
            WHERE o.id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Orcamento o = new Orcamento();
                o.setId(rs.getInt("id"));
                o.setDescricao(rs.getString("descricao"));
                o.setValor(rs.getDouble("valor"));
                o.setIdProfissional(rs.getInt("id_profissional"));
                o.setIdPaciente(rs.getInt("id_paciente"));
                o.setNomeProfissional(rs.getString("nome_profissional"));
                o.setNomePaciente(rs.getString("nome_paciente"));

                // Converte string de IDs para lista de inteiros
                String idsStr = rs.getString("id_procedimentos");
                List<Integer> idsProcedimentos = new ArrayList<>();
                if (idsStr != null && !idsStr.isEmpty()) {
                    for (String s : idsStr.split(",")) {
                        idsProcedimentos.add(Integer.parseInt(s.trim()));
                    }
                }
                o.setIdsProcedimentos(idsProcedimentos);

                return o;
            }

        } catch (SQLException e) {
            System.err.println("Erro SQL ao buscar orçamento: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro de conexão ao buscar orçamento: " + e.getMessage());
        }

        return null;
    }

    public List<Orcamento> listarTodos() {
        List<Orcamento> lista = new ArrayList<>();

        String sql = """
            SELECT o.*, p.nome AS nome_paciente, pr.nome AS nome_profissional
            FROM orcamento o
            LEFT JOIN paciente p ON o.id_paciente = p.id
            LEFT JOIN profissional pr ON o.id_profissional = pr.id_profissional
            ORDER BY o.id DESC
        """;

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
                o.setNomeProfissional(rs.getString("nome_profissional"));
                o.setNomePaciente(rs.getString("nome_paciente"));

                String idsStr = rs.getString("id_procedimentos");
                List<Integer> idsProcedimentos = new ArrayList<>();
                if (idsStr != null && !idsStr.isEmpty()) {
                    for (String s : idsStr.split(",")) {
                        idsProcedimentos.add(Integer.parseInt(s.trim()));
                    }
                }
                o.setIdsProcedimentos(idsProcedimentos);

                lista.add(o);
            }

        } catch (SQLException e) {
            System.err.println("Erro SQL ao listar orçamentos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro de conexão ao listar orçamentos: " + e.getMessage());
        }

        return lista;
    }

    public List<Orcamento> buscarPorDescricao(String descricao) {
        List<Orcamento> lista = new ArrayList<>();

        String sql = """
            SELECT o.*, p.nome AS nome_paciente, pr.nome AS nome_profissional
            FROM orcamento o
            LEFT JOIN paciente p ON o.id_paciente = p.id
            LEFT JOIN profissional pr ON o.id_profissional = pr.id_profissional
            WHERE o.descricao LIKE ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + descricao + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Orcamento o = new Orcamento();
                o.setId(rs.getInt("id"));
                o.setDescricao(rs.getString("descricao"));
                o.setValor(rs.getDouble("valor"));
                o.setIdProfissional(rs.getInt("id_profissional"));
                o.setIdPaciente(rs.getInt("id_paciente"));
                o.setNomeProfissional(rs.getString("nome_profissional"));
                o.setNomePaciente(rs.getString("nome_paciente"));

                String idsStr = rs.getString("id_procedimentos");
                List<Integer> idsProcedimentos = new ArrayList<>();
                if (idsStr != null && !idsStr.isEmpty()) {
                    for (String s : idsStr.split(",")) {
                        idsProcedimentos.add(Integer.parseInt(s.trim()));
                    }
                }
                o.setIdsProcedimentos(idsProcedimentos);

                lista.add(o);
            }

        } catch (SQLException e) {
            System.err.println("Erro SQL ao buscar orçamentos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro de conexão ao buscar orçamentos: " + e.getMessage());
        }

        return lista;
    }
}
