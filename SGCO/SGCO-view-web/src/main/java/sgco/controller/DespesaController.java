package sgco.controller;

import model.domain.Despesa;
import model.dao.DespesaDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/DespesaController")
public class DespesaController extends HttpServlet {


    private DespesaDAO dao = new DespesaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if ("editar".equals(acao)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Despesa d = dao.buscarPorId(id);

            request.setAttribute("despesa", d);
            request.getRequestDispatcher("editar.jsp").forward(request, response);

        } else if ("excluir".equals(acao)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.excluir(id);
            response.sendRedirect("relatorio.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if ("registrar".equals(acao)) {
            Despesa d = new Despesa();
            d.setDescricao(request.getParameter("descricao"));
            d.setValor(Double.parseDouble(request.getParameter("valor")));
            d.setPagamento(request.getParameter("pagamento"));

            dao.inserir(d);
            response.sendRedirect("relatorio.jsp");

        } else if ("atualizar".equals(acao)) {
            Despesa d = new Despesa();
            d.setId(Integer.parseInt(request.getParameter("id")));
            d.setDescricao(request.getParameter("descricao"));
            d.setValor(Double.parseDouble(request.getParameter("valor")));
            d.setPagamento(request.getParameter("pagamento"));

            dao.atualizar(d);
            response.sendRedirect("relatorio.jsp");
        }
    }
}
