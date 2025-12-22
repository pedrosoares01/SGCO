package sgco.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sgco.model.dao.DespesaDAO;
import sgco.model.dao.ReceitaDAO;
import sgco.model.domain.RelatorioFinanceiro;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;

@WebServlet("/financeiro")
public class RelatorioFinanceiroController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = (Connection) request
                .getServletContext()
                .getAttribute("connection");

      
        ReceitaDAO receitaDAO = new ReceitaDAO();
        DespesaDAO despesaDAO = new DespesaDAO();


        try {
            request.setAttribute("listaReceitas", receitaDAO.listarReceitas());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            request.setAttribute("listaDespesas", despesaDAO.listarDespesas());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        BigDecimal totalReceitas = null;
        try {
            totalReceitas = receitaDAO.totalReceitas();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        BigDecimal totalDespesas = null;
        try {
            totalDespesas = despesaDAO.totalDespesas();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        RelatorioFinanceiro relatorio = new RelatorioFinanceiro();
        relatorio.setTotalReceitas(totalReceitas);
        relatorio.setTotalDespesas(totalDespesas);
        relatorio.calcularSaldo();

        request.setAttribute("relatorio", relatorio);

      
        request.getRequestDispatcher("core/receita/pagina.jsp")
               .forward(request, response);
    }
}
