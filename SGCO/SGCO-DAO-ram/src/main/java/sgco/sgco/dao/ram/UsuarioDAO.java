package sgco.sgco.dao.ram;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sgco.sgco.domain.Usuario;


    
public class UsuarioDAO {    
    public void cadastrar(Usuario usuario) throws Exception{
    
        Connection conexao;
        PreparedStatement st;
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestaousuarios", "root", "rjm30.03.24");
        String sql = "INSERT INTO usuarios VALUES (?,?,?,?,?)";
        st = conexao.prepareStatement(sql);
        
        st.setString(1, usuario.getNome());
        st.setString(2, usuario.getEmail());
        st.setString(3, usuario.getSenha());
        st.setString(4, usuario.getCargo());
        st.setInt(5, usuario.getId());
        st.executeUpdate();
    }
    
    public void atualizar(Usuario usuario) throws Exception {
        Connection conexao;
        PreparedStatement st;
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestaousuarios", "root", "rjm30.03.24");
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ?, cargo = ? WHERE id = ?";
        st = conexao.prepareStatement(sql);
        st.setString(1, usuario.getNome());
        st.setString(2, usuario.getEmail());
        st.setString(3, usuario.getSenha());
        st.setString(4, usuario.getCargo());
        st.setInt(5, usuario.getId());
        st.executeUpdate();
    }
    
    public void excluir(Usuario usuario) throws Exception {
        Connection conexao;
        PreparedStatement st;
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestaousuarios" , "root", "rjm30.03.24");
        String sql = "DELETE FROM usuarios WHERE id = ? AND email = ? AND nome = ?";
        st = conexao.prepareStatement(sql);
        st.setString(3, usuario.getNome());
        st.setString(2, usuario.getEmail());
        st.setInt(1, usuario.getId());
        st.executeUpdate();
    }
    
}
