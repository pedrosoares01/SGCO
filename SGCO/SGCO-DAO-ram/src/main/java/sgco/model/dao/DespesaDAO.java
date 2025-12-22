package sgco.model.dao;

import sgco.model.domain.Despesa;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DespesaDAO {

    private Connection connection;
    
    public List<Despesa> listarDespesas() throws Exception{
        connection = ConnectionFactory.getConnection();
        List<Despesa> lista = new ArrayList<>();

        String sql = """
            SELECT descricao, valor, forma_pagamento, data_despesa
            FROM despesa
            ORDER BY data_despesa DESC
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Despesa d = new Despesa();
                d.setDescricao(rs.getString("descricao"));
                d.setValor(rs.getBigDecimal("valor"));
                d.setFormaPagamento(rs.getString("forma_pagamento"));
                d.setData(rs.getDate("data_despesa"));

                lista.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    
    public BigDecimal totalDespesas() throws Exception{
        connection = ConnectionFactory.getConnection();
        String sql = "SELECT SUM(valor) FROM despesa";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getBigDecimal(1) == null
                        ? BigDecimal.ZERO
                        : rs.getBigDecimal(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return BigDecimal.ZERO;
    }
}
