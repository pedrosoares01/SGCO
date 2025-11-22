package sgco.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sgco.sgco.domain.Usuario;


    
public class UsuarioDAO {    
    public void cadastrar(Usuario usuario) throws Exception{

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement st;
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
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement st;
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
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement st;
        String sql = "DELETE FROM usuarios WHERE id = ? AND email = ? AND nome = ?";
        st = conexao.prepareStatement(sql);
        st.setString(3, usuario.getNome());
        st.setString(2, usuario.getEmail());
        st.setInt(1, usuario.getId());
        st.executeUpdate();
    }

    public Usuario pesquisar(Usuario usuario) throws Exception {
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement st;
        String sql = "SELECT * FROM usuarios WHERE  nome = ?";
        st = conexao.prepareStatement(sql);
        st.setString(1, usuario.getNome());
        ResultSet result = st.executeQuery();

        Usuario usuarioRetorno = null;

        if (result.next()) {
            usuarioRetorno = new Usuario();
            usuarioRetorno.setId(result.getInt("id"));
            usuarioRetorno.setNome(result.getString("nome"));
            usuarioRetorno.setEmail(result.getString("email"));
            usuarioRetorno.setSenha(result.getString("senha"));
            usuarioRetorno.setCargo(result.getString("cargo"));
            return  usuarioRetorno;
        }


        throw new Exception("Usuário não encontrado!");
    }

    public Usuario logar(Usuario usuario) throws Exception {
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement st;
        String sql = "SELECT * FROM usuarios WHERE  nome = ?";
        st = conexao.prepareStatement(sql);
        st.setString(1, usuario.getNome());
        ResultSet result = st.executeQuery();

        Usuario usuarioRetorno = null;

        if (result.next()) {
            usuarioRetorno = new Usuario();
            usuarioRetorno.setSenha(result.getString("senha"));
            usuarioRetorno.setCargo(result.getString("cargo"));
            return  usuarioRetorno;
        }

        throw new Exception("Usuário não encontrado!");
    }

}
