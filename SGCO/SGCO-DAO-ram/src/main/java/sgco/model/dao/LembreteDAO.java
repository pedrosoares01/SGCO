package sgco.model.dao;

import sgco.model.domain.Paciente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LembreteDAO {
    public Paciente LembrarPaciente(Paciente paciente) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "root", "Sgco1234");
        PreparedStatement st = conn.prepareStatement("SELECT email FROM paciente WHERE nome LIKE ?");
        st.setString(1, paciente.getNome());
        ResultSet result = st.executeQuery();
        if (result.next()) {
            paciente.setEmail(result.getString("email"));
        }
        st.close();
        conn.close();
        return  paciente;
    }
}
