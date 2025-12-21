package sgco.model.dao;

import sgco.sgco.domain.SaldoPaciente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SaldoDAO {


    public List<SaldoPaciente> resgatarDividas() throws Exception {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st;
        String sql = """
            SELECT
            p.id AS paciente_id,
            o.id AS orcamento_id,
            p.nome AS nome_paciente,
            o.valor AS valor_orcamento,
            COALESCE(SUM(pg.valor_pago), 0) AS total_pago,
            (o.valor - COALESCE(SUM(pg.valor_pago), 0)) AS valor_devido
            FROM paciente p
            JOIN orcamento o ON o.id_paciente = p.id
            LEFT JOIN pagamento pg ON pg.orcamento_id = o.id
            GROUP BY p.id, o.id, p.nome, o.valor
            HAVING valor_devido > 0
        """;
        st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        List<SaldoPaciente> listaDosSaldos = new ArrayList<>();
        while (rs.next()) {
            SaldoPaciente s = new SaldoPaciente();
            s.setNomeDevedor(rs.getString("nome_paciente"));
            s.setPago(rs.getDouble("total_pago"));
            s.setDevido(rs.getDouble("valor_devido"));
            s.setTotalPagar(rs.getDouble("valor_orcamento"));
            s.setPacienteId(rs.getInt("paciente_id"));
            s.setOrcamentoId(rs.getInt("orcamento_id"));
            listaDosSaldos.add(s);
        }
        if (listaDosSaldos.isEmpty())
            throw new Exception("Nenhuma correspondecia");
        rs.close();
        st.close();
        conn.close();
        return listaDosSaldos;
    }

    public List<SaldoPaciente> pesquisarDividas(SaldoPaciente saldoPaciente) throws Exception {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st;
        String sql = """
            SELECT
            p.id AS paciente_id,
            o.id AS orcamento_id,
            p.nome AS nome_paciente,
            o.valor AS valor_orcamento,
            COALESCE(SUM(pg.valor_pago), 0) AS total_pago,
            (o.valor - COALESCE(SUM(pg.valor_pago), 0)) AS valor_devido
            FROM paciente p
            JOIN orcamento o ON o.id_paciente = p.id
            LEFT JOIN pagamento pg ON pg.orcamento_id = o.id
            WHERE p.nome LIKE ?
            GROUP BY p.id, o.id, p.nome, o.valor
            HAVING valor_devido > 0
        """;
        st = conn.prepareStatement(sql);
        st.setString(1, "%" +saldoPaciente.getNomeDevedor() + "%");
        ResultSet rs = st.executeQuery();
        List<SaldoPaciente> listaDosSaldos = new ArrayList<>();
        while (rs.next()) {
            SaldoPaciente s = new SaldoPaciente();
            s.setNomeDevedor(rs.getString("nome_paciente"));
            s.setPago(rs.getDouble("total_pago"));
            s.setDevido(rs.getDouble("valor_devido"));
            s.setTotalPagar(rs.getDouble("valor_orcamento"));
            s.setPacienteId(rs.getInt("paciente_id"));
            s.setOrcamentoId(rs.getInt("orcamento_id"));
            listaDosSaldos.add(s);
        }
        if (listaDosSaldos.isEmpty())
            throw new Exception("Nenhuma correspondecia");
        rs.close();
        st.close();
        conn.close();
        return listaDosSaldos;
    }

    public void atualizarSaldos(SaldoPaciente saldoPaciente) throws Exception {

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st;

        String sql = " UPDATE pagamento SET valor_pago = ? WHERE orcamento_id = ?";
        st = conn.prepareStatement(sql);
        st.setDouble(1, saldoPaciente.getPago());
        st.setInt(2, saldoPaciente.getOrcamentoId());
        st.executeUpdate();


        sql = " UPDATE orcamento SET valor = ? WHERE id_paciente = ?";
        st = conn.prepareStatement(sql);
        st.setDouble(1, saldoPaciente.getTotalPagar());
        st.setInt(2, saldoPaciente.getPacienteId());
        st.executeUpdate();
    }


}
