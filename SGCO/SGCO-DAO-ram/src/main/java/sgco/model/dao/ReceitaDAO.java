package sgco.model.dao;

import sgco.model.domain.Receita;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class ReceitaDAO {

    private Connection connection;

    public List<Receita> listarReceitas() {
        connection = ConnectionFactory.getConnection();
        List<Receita> lista = new ArrayList<>();

        String sql = """
            SELECT valor_pago, forma_pagamento, data_pagamento
            FROM pagamento
            ORDER BY data_pagamento DESC
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Receita r = new Receita();
                r.setDescricao("Pagamento de procedimento");
                r.setValor(rs.getBigDecimal("valor_pago"));
                r.setFormaPagamento(rs.getString("forma_pagamento"));
                r.setData(rs.getDate("data_pagamento"));

                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


    public BigDecimal totalReceitas() {
        connection = ConnectionFactory.getConnection();
        String sql = "SELECT SUM(valor_pago) FROM pagamento";

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
