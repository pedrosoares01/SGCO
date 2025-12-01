package sgco.model.dao;
import java.sql.*;
import sgco.sgco.domain.Agenda;
import sgco.sgco.domain.Usuario;

import java.util.List;
import java.util.ArrayList;

public class AgendaDAO {
    public void agendar(Agenda agenda) throws Exception {
        Connection conn = ConnectionFactory.getConnection();
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
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st;
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
    public List<Usuario> listarProfissionais() throws Exception {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st;
        String sql = "SELECT * FROM usuarios WHERE cargo = 'Profissional'";
        st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        List<Usuario> lista = new ArrayList<>();
        while (rs.next()) {
            Usuario p = new Usuario();
            p.setNome(rs.getString("nome"));
            lista.add(p);
        }
        if (lista.isEmpty())
            throw new Exception("Nenhuma correspondecia");
        rs.close();
        st.close();
        conn.close();
        return lista;
    }
    public List<Agenda> listarHorariosOcupados(String profissional, String data) throws Exception {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM agenda WHERE profissional = ? AND data_agendamento = ? ORDER BY hora_agendamento";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, profissional);
        st.setString(2, data);
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
        rs.close();
        st.close();
        conn.close();
        return lista;
    }
}