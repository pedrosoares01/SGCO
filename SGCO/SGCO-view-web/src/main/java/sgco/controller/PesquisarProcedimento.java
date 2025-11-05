package sgco.controller;

import sgco.model.dao.ProcedimentoDAO;
import sgco.model.domain.Procedimento;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "PesquisarProcedimento", urlPatterns = {"/PesquisarProcedimento"})
public class PesquisarProcedimento extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nomeProcedimento = request.getParameter("searchProcedimento");

        ProcedimentoDAO dao = new ProcedimentoDAO();
        List<Procedimento> resultados = dao.pesquisarPorNome(nomeProcedimento);

        request.setAttribute("resultados", resultados);
        request.getRequestDispatcher("/core/procedimentos/pagina.jsp").forward(request, response);
    }
}
