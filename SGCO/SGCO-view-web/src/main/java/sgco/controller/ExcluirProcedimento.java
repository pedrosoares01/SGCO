package sgco.controller;

import sgco.model.dao.ProcedimentoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ExcluirProcedimento", urlPatterns = {"/ExcluirProcedimento"})
public class ExcluirProcedimento extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            enviarMensagem(request, response, "ID não informado!", "erro");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);

            ProcedimentoDAO dao = new ProcedimentoDAO();
            boolean sucesso = dao.excluir(id);

            if (sucesso) {
                enviarMensagem(request, response, "Procedimento excluído com sucesso!", "sucesso");
            } else {
                enviarMensagem(request, response, "Erro ao excluir o procedimento.", "erro");
            }

        } catch (NumberFormatException e) {
            enviarMensagem(request, response, "ID inválido.", "erro");
        } catch (Exception e) {
            e.printStackTrace();
            enviarMensagem(request, response, "Erro ao excluir: " + e.getMessage(), "erro");
        }
    }

    private void enviarMensagem(HttpServletRequest request, HttpServletResponse response,
                                String mensagem, String tipo)
            throws ServletException, IOException {
        request.setAttribute("mensagem", mensagem);
        request.setAttribute("tipoMensagem", tipo);
        request.getRequestDispatcher("/core/procedimentos/pagina.jsp").forward(request, response);
    }
}