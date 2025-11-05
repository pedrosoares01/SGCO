package sgco.sgco.service;

import java.sql.SQLException;
import sgco.sgco.dao.ram.UsuarioDAO;
import sgco.sgco.domain.Usuario;

public class GestaoUsuarioService {
    
    private UsuarioDAO usuarioDAO;

    public GestaoUsuarioService() {
        usuarioDAO = new UsuarioDAO();
    }

    public void cadastrar(Usuario usuario) throws Exception{
        usuarioDAO.cadastrar(usuario);
    }
    
    public void atualizar(Usuario usuario) throws Exception {
        usuarioDAO.atualizar(usuario);
    }
    
    public void excluir(Usuario usuario) throws Exception {
        usuarioDAO.excluir(usuario);
    }
}
