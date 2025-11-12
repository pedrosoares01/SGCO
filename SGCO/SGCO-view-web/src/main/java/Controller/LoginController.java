package Controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sgco.sgco.domain.Usuario;
import sgco.sgco.service.LoginService;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.getParameter("nome");
            request.getParameter("senha");

            Usuario usuario = new Usuario();

            usuario.setNome(request.getParameter("nome"));
            usuario.setSenha(request.getParameter("senha"));

            LoginService loginService = new LoginService();

            Usuario usuarioRetorno = loginService.login(usuario);

            request.getSession().setAttribute("usuarioLogado", usuarioRetorno);

            switch (usuarioRetorno.getCargo()) {

                case "Gerente":
                    response.sendRedirect(request.getContextPath() + "/indexgerente.jsp");
                    break;
                case "Recepcionista":
                    response.sendRedirect(request.getContextPath() + "/indexrecepcionista.jsp");
                    break;
                case "Profissional":
                    response.sendRedirect(request.getContextPath() + "/indexprofissional.jsp");
                    break;
            }


        }catch (Exception e){
            request.getRequestDispatcher("erro.jsp?msg=" + e.getMessage()).forward(request, response);
        }
    }

    public static void validarSessao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }


}