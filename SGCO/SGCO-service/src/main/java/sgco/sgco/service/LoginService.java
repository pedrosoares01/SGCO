package sgco.sgco.service;

import criptografia.Criptografia;
import sgco.sgco.dao.ram.UsuarioDAO;
import sgco.sgco.domain.Usuario;

public class LoginService {

    private UsuarioDAO usuarioDAO;

    public LoginService() {
        usuarioDAO = new UsuarioDAO();
    }

    public Usuario login(Usuario usuario) throws Exception {

        validar(usuario);

        Usuario usuarioRetorno = usuarioDAO.logar(usuario);
        if (Criptografia.verificarSenha(usuario.getSenha(), usuarioRetorno.getSenha())){
            return usuarioRetorno;
        }
        else  throw new Exception("senha incorreta!");
    }

    private void validar(Usuario usuario) throws Exception {

        if(usuario.getSenha() == null || usuario.getSenha().isBlank()){
            throw new Exception("a senha não foi preenchida");
        }

        if(usuario.getNome() == null || usuario.getNome().isBlank()){
            throw new Exception("o nome não foi preenchido");
        }
    }


}
