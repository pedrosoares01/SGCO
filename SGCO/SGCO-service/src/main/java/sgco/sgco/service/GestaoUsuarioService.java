package sgco.sgco.service;

import criptografia.Criptografia;
import sgco.sgco.dao.ram.UsuarioDAO;
import sgco.sgco.domain.Usuario;

public class GestaoUsuarioService {
    
    private UsuarioDAO usuarioDAO;

    public GestaoUsuarioService() {
        usuarioDAO = new UsuarioDAO();
    }

    public void cadastrar(Usuario usuario) throws Exception{

        validacao(usuario);

        String senha =  usuario.getSenha();

        Criptografia criptografia = null;

        String senhaCriptografada = Criptografia.criptografar(senha);

        usuario.setSenha(senhaCriptografada);

        usuarioDAO.cadastrar(usuario);
    }
    
    public void atualizar(Usuario usuario) throws Exception {

        validacao(usuario);

        String senha =  usuario.getSenha();

        Criptografia criptografia = null;

        String senhaCriptografada = Criptografia.criptografar(senha);

        usuario.setSenha(senhaCriptografada);

        usuarioDAO.atualizar(usuario);
    }
    
    public void excluir(Usuario usuario) throws Exception {

        validacao(usuario);

        usuarioDAO.excluir(usuario);
    }

    public Usuario pesquisar(Usuario usuario) throws Exception {

        return usuarioDAO.pesquisar(usuario);
    }

    private void validacao(Usuario usuario) throws Exception{

        if(usuario.getSenha() == null || usuario.getSenha().isBlank()){
            throw new Exception("a senha não foi preenchida");
        }

        if(usuario.getEmail() == null || usuario.getEmail().isBlank()){
            throw new Exception("o email não foi preenchido");
        }

        if(usuario.getNome() == null || usuario.getNome().isBlank()){
            throw new Exception("o nome não foi preenchido");
        }

        if(usuario.getCargo() == null || usuario.getCargo().isBlank()){
            throw new Exception("o cargo não foi preenchido");
        }

        if (usuario.getId() < 1){
            throw new Exception("Id invalido");
        }

    }

}
