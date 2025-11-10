package sgco.sgco.dao.ram;
import java.sql.*;
import sgco.sgco.domain.Agenda;
import java.util.List;
import java.util.ArrayList;

public class AgendaDAO {
    public void agendar(Agenda agenda) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "sgcopass");
        PreparedStatement st = conn.prepareStatement("INSERT INTO agenda (paciente, profissional, data_agendamento, hora_agendamento) VALUES (?, ?, ?, ?)");
        st.setString(1, agenda.getPaciente());
        st.setString(2, agenda.getProfissional());
        st.setString(3, agenda.getData());
        st.setString(4, agenda.getHora());
        st.executeUpdate();
        st.close();
        conn.close();
    }
    public List<Agenda> pesquisar(Agenda agenda) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn;
        PreparedStatement st;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "sgcopass");
        String sql = "SELECT * FROM agenda WHERE paciente LIKE ? OR profissional LIKE ? ORDER BY data_agendamento, hora_agendamento";
        st = conn.prepareStatement(sql);
        st.setString(1, "%" + agenda.getPaciente() + "%");
        st.setString(2, "%" + agenda.getProfissional() + "%");
        ResultSet rs = st.executeQuery();
        List<Agenda> lista = new ArrayList<>();
        while (rs.next()) {
            Agenda a = new Agenda();
            a.setPaciente(rs.getString("paciente"));
            a.setProfissional(rs.getString("profissional"));
            a.setData(rs.getString("data_agendamento"));
            a.setHora(rs.getString("hora_agendamento"));
            lista.add(a);
        }
        if (lista.isEmpty())
            throw new Exception("Nenhuma correspondecia");
        rs.close();
        st.close();
        conn.close();
        return lista;
    }
}