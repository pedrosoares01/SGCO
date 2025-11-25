package sgco.sgco.dao.ram;

import sgco.model.domain.Paciente;
import sgco.sgco.domain.Agenda;
import sgco.sgco.domain.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LembreteDAO {
    public Paciente LembrarPaciente(Paciente paciente) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "sgcopass");
        PreparedStatement st = conn.prepareStatement("SELECT email FROM paciente WHERE nome = ?");
        st.setString(1, paciente.getNome());
        ResultSet result = st.executeQuery();

        if (result.next()) {
            paciente.setEmail(result.getString("email"));
            return  paciente;
        }
        st.close();
        conn.close();
        throw new Exception("Paciente não encontrado");
    }
}
