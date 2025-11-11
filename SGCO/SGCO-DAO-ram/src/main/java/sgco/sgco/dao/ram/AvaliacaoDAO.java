package sgco.sgco.dao.ram;

import sgco.sgco.domain.Avaliacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDAO {
    public List<Avaliacao> avaliar(Avaliacao avaliacao) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/avaliacao", "root", "sgcopass");
        String sqlInsert = "INSERT INTO avaliacao(profissional, nota) VALUES (?, ?)";
        PreparedStatement stInsert = conn.prepareStatement(sqlInsert);
        stInsert.setString(1, avaliacao.getProfissional());
        stInsert.setInt(2, avaliacao.getNota());
        stInsert.executeUpdate();
        stInsert.close();
        String sqlSelect = "SELECT profissional, nota FROM avaliacao";
        PreparedStatement stSelect = conn.prepareStatement(sqlSelect);
        ResultSet rs = stSelect.executeQuery();
        List<Avaliacao> lista = new ArrayList<>();
        while (rs.next()) {
            Avaliacao av = new Avaliacao();
            av.setProfissional(rs.getString("profissional"));
            av.setNota(rs.getInt("nota"));
            lista.add(av);
        }
        rs.close();
        stSelect.close();
        conn.close();
        return lista;
    }
}