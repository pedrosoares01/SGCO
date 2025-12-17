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
                p.nome AS nome_paciente,
                o.valor AS valor_orcamento,
                COALESCE(SUM(pg.valor_pago), 0) AS total_pago,
                (o.valor - COALESCE(SUM(pg.valor_pago), 0)) AS valor_devido
            FROM paciente p
            JOIN orcamento o ON o.id_paciente = p.id
            LEFT JOIN pagamento pg ON pg.orcamento_id = o.id
            GROUP BY p.nome, o.valor
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
            listaDosSaldos.add(s);
        }
        if (listaDosSaldos.isEmpty())
            throw new Exception("Nenhuma correspondecia");
        rs.close();
        st.close();
        conn.close();
        return listaDosSaldos;
    }


}
