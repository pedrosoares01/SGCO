package sgco.sgco.dao.ram;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


    
public class UsuarioDAO {    
    public void cadastrar(String nome, String email, String senha, String cargo) throws ClassNotFoundException, SQLException{
    
        Connection conexao;
        PreparedStatement st;
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestaousuarios");
        
        st = conexao.prepareStatement("INSERT INTO usuarios VALUES (?,?,?,?)");
        
        st.setString(1, nome);
        st.setString(2, email);
        st.setString(3, senha);
        st.setString(4, cargo);
        st.executeUpdate();
    }
}
