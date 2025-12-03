package Controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sgco.sgco.domain.Usuario;
import sgco.sgco.service.GestaoUsuarioService;

@WebServlet(name = "UsuarioController", urlPatterns = {"/UsuarioController"})
public class UsuarioController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if (acao == null) {
            acao = "pesquisar";
        }

        switch (acao) {
            case "atualizar":
                atualizarUsuario(request, response);
                break;
            case "cadastrar":
                cadastrarUsuario(request, response);
                break;
            case "excluir":
                excluirUsuario(request,response);
                break;
            case "pesquisar":
                pesquisarUsuario(request,response);
                break;
            default:
                break;
        }
    }

    private void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String cargo = request.getParameter("cargo");
            int id = Integer.parseInt(request.getParameter("id"));

            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setCargo(cargo);

            GestaoUsuarioService usuarioService = new GestaoUsuarioService();
            usuarioService.cadastrar(usuario);
            request.setAttribute("msg", "Cadastro realizado com sucesso!");
            request.getRequestDispatcher("gestaoUsuarios/gestao_usuarios.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("erro.jsp?msg=" + e.getMessage()).forward(request, response);
        }
    }

    private void atualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String cargo = request.getParameter("cargo");

            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setCargo(cargo);

            GestaoUsuarioService usuarioService = new GestaoUsuarioService();
            usuarioService.atualizar(usuario);
            request.setAttribute("msg", "Atualização realizada com sucesso!");
            request.getRequestDispatcher("gestaoUsuarios/gestao_usuarios.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("erro.jsp?msg=" + e.getMessage()).forward(request, response);
        }
    }
    
    private void excluirUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String cargo = request.getParameter("cargo");

            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setCargo(cargo);

            GestaoUsuarioService usuarioService = new GestaoUsuarioService();
            usuarioService.excluir(usuario);
            request.setAttribute("msg", "Exclusao realizada com sucesso!");
            request.getRequestDispatcher("gestaoUsuarios/gestao_usuarios.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("erro.jsp?msg=" + e.getMessage()).forward(request, response);
        }
    }

    private void pesquisarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String nome = request.getParameter("nome");

            Usuario usuario = new Usuario();
            Usuario usuarioRetorno = new Usuario();
            usuario.setNome(nome);

            GestaoUsuarioService usuarioService = new GestaoUsuarioService();
            usuarioRetorno = usuarioService.pesquisar(usuario);

            request.setAttribute("id", usuarioRetorno.getId());
            request.setAttribute("nome", usuarioRetorno.getNome());
            request.setAttribute("email", usuarioRetorno.getEmail());
            request.setAttribute("cargo", usuarioRetorno.getCargo());
            request.setAttribute("senha", "");

            request.getRequestDispatcher("gestaoUsuarios/gestao_usuarios.jsp").forward(request, response);

        } catch (Exception e) {
            request.getRequestDispatcher("erro.jsp?msg=" + e.getMessage()).forward(request, response);
        }
    }

}
