package sgco.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import sgco.model.domain.Equipamentos;

public class EquipamentosDAO {

    // INSERIR
    public boolean inserir(Equipamentos e) {
        String sql = "INSERT INTO equipamentos (nome, codigo, local, ultima, freq, status) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getCodigo());
            stmt.setString(3, e.getLocal());
            stmt.setString(4, e.getUltima());
            stmt.setInt(5, e.getFreq());
            stmt.setString(6, e.getStatus());

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Erro SQL ao inserir equipamento: " + ex.getMessage());
            return false;
        }
    }
    
    public List<Equipamentos> pesquisarPorNome(String nome) {
        List<Equipamentos> lista = new ArrayList<>();

        String sql;
        if (nome == null || nome.trim().isEmpty()) {
            sql = "SELECT * FROM equipamentos ORDER BY id DESC";
        } else {
            sql = "SELECT * FROM equipamentos WHERE nome LIKE ? ORDER BY id DESC";
        }

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (nome != null && !nome.trim().isEmpty()) {
                stmt.setString(1, "%" + nome + "%");
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Equipamentos e = new Equipamentos(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("codigo"),
                        rs.getString("local"),
                        rs.getString("ultima"),
                        rs.getInt("freq"),
                        rs.getString("status")
                );
                lista.add(e);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao pesquisar equipamentos: " + ex.getMessage());
        }

        return lista;
    }

    // LISTAR TODOS
    public List<Equipamentos> listarTodos() {
        List<Equipamentos> lista = new ArrayList<>();
        String sql = "SELECT * FROM equipamentos ORDER BY id DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Equipamentos e = new Equipamentos(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("codigo"),
                        rs.getString("local"),
                        rs.getString("ultima"),
                        rs.getInt("freq"),
                        rs.getString("status")
                );
                lista.add(e);
            }

        } catch (SQLException ex) {
            System.err.println("Erro SQL ao listar equipamentos: " + ex.getMessage());
        }

        return lista;
    }

    // BUSCAR POR ID
    public Equipamentos buscarPorId(int id) {
        String sql = "SELECT * FROM equipamentos WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Equipamentos(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("codigo"),
                        rs.getString("local"),
                        rs.getString("ultima"),
                        rs.getInt("freq"),
                        rs.getString("status")
                );
            }

        } catch (SQLException ex) {
            System.err.println("Erro SQL ao buscar equipamento por ID: " + ex.getMessage());
        }

        return null;
    }

    // ATUALIZAR
    public boolean atualizar(Equipamentos e) {
        String sql = "UPDATE equipamentos SET nome = ?, codigo = ?, local = ?, ultima = ?, freq = ?, status = ? "
                   + "WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getCodigo());
            stmt.setString(3, e.getLocal());
            stmt.setString(4, e.getUltima());
            stmt.setInt(5, e.getFreq());
            stmt.setString(6, e.getStatus());
            stmt.setInt(7, e.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Erro SQL ao atualizar equipamento: " + ex.getMessage());
            return false;
        }
    }

    // EXCLUIR
    public boolean excluir(int id) {
        String sql = "DELETE FROM equipamentos WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Erro SQL ao excluir equipamento: " + ex.getMessage());
            return false;
        }
    }
}