package sgco.controller;

import sgco.model.dao.ReceitaDAO;
import sgco.model.dao.DespesaDAO;
import sgco.model.domain.RelatorioFinanceiro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

      
        ReceitaDAO receitaDAO = new ReceitaDAO(conn);
        DespesaDAO despesaDAO = new DespesaDAO(conn);

        
        request.setAttribute("listaReceitas", receitaDAO.listarReceitas());
        request.setAttribute("listaDespesas", despesaDAO.listarDespesas());

       
        BigDecimal totalReceitas = receitaDAO.totalReceitas();
        BigDecimal totalDespesas = despesaDAO.totalDespesas();

        RelatorioFinanceiro relatorio = new RelatorioFinanceiro();
        relatorio.setTotalReceitas(totalReceitas);
        relatorio.setTotalDespesas(totalDespesas);
        relatorio.calcularSaldo();

        request.setAttribute("relatorio", relatorio);

      
        request.getRequestDispatcher("/pages/receita/pagina.jsp")
               .forward(request, response);
    }
}
