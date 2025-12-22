package sgco.controller;

import sgco.model.dao.ReceitaDAO;
import sgco.model.domain.Receita;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/receita")
public class ReceitaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = (Connection) request.getServletContext().getAttribute("connection");

        ReceitaDAO dao = new ReceitaDAO(conn);
        List<Receita> receitas = dao.listarReceitas();

        request.setAttribute("listaReceitas", receitas);
        request.getRequestDispatcher("/pages/receita/pagina.jsp").forward(request, response);
    }
}
