package sgco.sgco.service;

import java.sql.SQLException;
import sgco.sgco.dao.ram.UsuarioDAO;
import sgco.sgco.domain.Usuario;

public class GestaoUsuarioService {
    
    private UsuarioDAO usuarioDAO;
    private Usuario usuario;

    public void cadastrar(String nome, String email, String senha, String cargo) throws ClassNotFoundException, SQLException{
        try{
            usuarioDAO.cadastrar(nome, email, senha, cargo);
        }catch(ClassNotFoundException cl){
        
            
            
        }catch(SQLException e){
        
        }
        
    }
    
    
}
