package sgco.controller;

import sgco.model.dao.ProcedimentoDAO;
import sgco.model.domain.Procedimento;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AtualizarProcedimento", urlPatterns = {"/AtualizarProcedimento"})
public class AtualizarProcedimento extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        String nome = request.getParameter("nome");
        String precoStr = request.getParameter("preco");

        if (idStr == null || nome == null || precoStr == null ||
            idStr.isEmpty() || nome.isEmpty() || precoStr.isEmpty()) {

            request.setAttribute("mensagem", "Todos os campos são obrigatórios.");
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("/core/procedimentos/pagina.jsp").forward(request, response);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            double preco = Double.parseDouble(precoStr);

            Procedimento p = new Procedimento();
            p.setId(id);
            p.setNome(nome);
            p.setPreco(preco);

            ProcedimentoDAO dao = new ProcedimentoDAO();
            boolean sucesso = dao.atualizar(p);

            if (sucesso) {
                request.setAttribute("mensagem", "Procedimento atualizado com sucesso!");
                request.setAttribute("tipoMensagem", "sucesso");
            } else {
                request.setAttribute("mensagem", "Erro ao atualizar o procedimento.");
                request.setAttribute("tipoMensagem", "erro");
            }

            request.getRequestDispatcher("/core/procedimentos/pagina.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Formato inválido no ID ou preço.");
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("/core/procedimentos/pagina.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Erro inesperado: " + e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("/core/procedimentos/pagina.jsp").forward(request, response);
        }
    }
}
