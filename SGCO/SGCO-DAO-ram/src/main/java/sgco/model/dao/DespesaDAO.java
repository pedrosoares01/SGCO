package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.domain.Despesa;

public class DespesaDAO {

    public void inserir(Despesa d) throws Exception {
        String sql = "INSERT INTO despesa (descricao, valor, pagamento) VALUES (?, ?, ?)";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, d.getDescricao());
            ps.setBigDecimal(2, d.getValor());
            ps.setString(3, d.getPagamento());
            ps.execute();
        }
    }

    public List<Despesa> listar() throws Exception {
        List<Despesa> lista = new ArrayList<>();
        String sql = "SELECT * FROM despesa";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Despesa d = new Despesa();
                d.setId(rs.getInt("id"));
                d.setDescricao(rs.getString("descricao"));
                d.setValor(rs.getBigDecimal("valor"));
                d.setPagamento(rs.getString("pagamento"));
                lista.add(d);
            }
        }
        return lista;
    }

    public Despesa buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM despesa WHERE id = ?";
        Despesa d = null;

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                d = new Despesa();
                d.setId(id);
                d.setDescricao(rs.getString("descricao"));
                d.setValor(rs.getBigDecimal("valor"));
                d.setPagamento(rs.getString("pagamento"));
            }
        }
        return d;
    }

    public void atualizar(Despesa d) throws Exception {
        String sql = "UPDATE despesa SET descricao=?, valor=?, pagamento=? WHERE id=?";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, d.getDescricao());
            ps.setBigDecimal(2, d.getValor());
            ps.setString(3, d.getPagamento());
            ps.setInt(4, d.getId());
            ps.execute();
        }
    }

    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM despesa WHERE id=?";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.execute();
        }
    }
}
