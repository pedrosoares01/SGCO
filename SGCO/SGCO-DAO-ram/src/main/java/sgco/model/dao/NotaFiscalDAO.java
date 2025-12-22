package sgco.model.dao;

import sgco.model.domain.NotaFiscal;
import java.sql.*;

public class NotaFiscalDAO {

    private static final String INSERT =
    "INSERT INTO nota_fiscal (orcamento_id, valor_total, data_emissao, nome_paciente, nome_profissional, forma_pagamento) " +
    "VALUES (?, ?, ?, ?, ?, ?)";


    private static final String BUSCAR_POR_ORCAMENTO =
        "SELECT * FROM nota_fiscal WHERE orcamento_id = ?";

    public boolean inserir(NotaFiscal nota) {

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT)) {

            stmt.setInt(1, nota.getOrcamentoId());
            stmt.setDouble(2, nota.getValorTotal());
            stmt.setDate(3, java.sql.Date.valueOf(nota.getDataEmissao()));
            stmt.setString(4, nota.getNomePaciente());
            stmt.setString(5, nota.getNomeProfissional());
            stmt.setString(6, nota.getFormaPagamento());

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public NotaFiscal buscarPorOrcamento(int orcamentoId) {

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(BUSCAR_POR_ORCAMENTO)) {

            stmt.setInt(1, orcamentoId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                NotaFiscal nf = new NotaFiscal();
                nf.setId(rs.getInt("id"));
                nf.setOrcamentoId(rs.getInt("orcamento_id"));
                nf.setValorTotal(rs.getDouble("valor_total"));
                nf.setDataEmissao(rs.getDate("data_emissao").toLocalDate());
                nf.setNomePaciente(rs.getString("nome_paciente"));
                nf.setNomeProfissional(rs.getString("nome_profissional"));
                return nf;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
