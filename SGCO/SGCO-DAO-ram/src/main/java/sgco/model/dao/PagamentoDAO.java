package sgco.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sgco.model.domain.Pagamento;

public class PagamentoDAO {

    public boolean inserir(Pagamento pagamento) throws Exception {
        String sql = "INSERT INTO pagamento (orcamento_id, forma_pagamento, valor_pago, data_pagamento) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pagamento.getOrcamentoId());
            stmt.setString(2, pagamento.getFormaPagamento());
            stmt.setDouble(3, pagamento.getValorPago());
            stmt.setDate(4, Date.valueOf(pagamento.getDataPagamento()));

            return stmt.executeUpdate() > 0;
        }
    }

    public List<Pagamento> buscarPorOrcamento(int orcamentoId) throws Exception {

        List<Pagamento> lista = new ArrayList<>();

        String sql = "SELECT * FROM pagamento WHERE orcamento_id = ? ORDER BY data_pagamento DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orcamentoId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pagamento p = new Pagamento();

                    p.setId(rs.getInt("id"));
                    p.setOrcamentoId(rs.getInt("orcamento_id"));
                    p.setFormaPagamento(rs.getString("forma_pagamento"));
                    p.setValorPago(rs.getDouble("valor_pago"));
                    p.setDataPagamento(rs.getDate("data_pagamento").toLocalDate());

                    lista.add(p);
                }
            }
        }

        return lista;
    }
}
