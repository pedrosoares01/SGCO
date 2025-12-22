package sgco.controller;

import jakarta.servlet.http.HttpServlet;
import sgco.model.dao.ReceitaDAO;
import sgco.model.domain.Receita;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReceitaController", urlPatterns = {"/ReceitaController"})
public class ReceitaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        ReceitaDAO dao = new ReceitaDAO();
        List<Receita> receitas = dao.listarReceitas();

        request.setAttribute("listaReceitas", receitas);
        request.getRequestDispatcher("core/receita/pagina.jsp").forward(request, response);
    }
}
