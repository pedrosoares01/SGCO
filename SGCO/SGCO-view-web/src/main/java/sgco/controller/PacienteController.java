package sgco.controller;

import sgco.model.dao.PacienteDAO;
import sgco.model.domain.Paciente;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "PacienteController", urlPatterns = {"/PacienteController"})
public class PacienteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if ("pesquisar".equals(acao)) {
            pesquisar(request, response);
        }
    }

    private void pesquisar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("search");

        PacienteDAO pacienteDAO = new PacienteDAO();
        List<Paciente> resultados = pacienteDAO.pesquisarPorNome(nome);

        request.setAttribute("resultados", resultados);
        request.getRequestDispatcher("/core/orcamento/pagina.jsp").forward(request, response);
    }
}

