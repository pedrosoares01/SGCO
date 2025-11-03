package sgco.controller;

import sgco.model.dao.ProcedimentoDAO;
import sgco.model.domain.Procedimento;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EditarProcedimento", urlPatterns = {"/EditarProcedimento"})
public class EditarProcedimento extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        // 🟥 Verificação básica
        if (idStr == null || idStr.trim().isEmpty()) {
            enviarMensagem(request, response, "ID do procedimento não informado!", "erro");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);

            ProcedimentoDAO dao = new ProcedimentoDAO();
            Procedimento procedimento = dao.buscarPorId(id);

            // 🟩 Encaminha para edição se encontrado
            if (procedimento != null) {
                request.setAttribute("procedimento", procedimento);
                request.getRequestDispatcher("/core/procedimentos/editar.jsp").forward(request, response);
            } else {
                enviarMensagem(request, response, "Procedimento não encontrado.", "erro");
            }

        } catch (NumberFormatException e) {
            enviarMensagem(request, response, "ID inválido. Verifique e tente novamente.", "erro");

        } catch (Exception e) {
            e.printStackTrace(); // útil para depuração
            enviarMensagem(request, response, "Erro ao carregar o procedimento: " + e.getMessage(), "erro");
        }
    }

    // 🧩 Método auxiliar para exibir mensagens de erro e sucesso
    private void enviarMensagem(HttpServletRequest request, HttpServletResponse response,
                                String mensagem, String tipo)
            throws ServletException, IOException {

        request.setAttribute("mensagem", mensagem);
        request.setAttribute("tipoMensagem", tipo);
        request.getRequestDispatcher("/core/procedimentos/pagina.jsp").forward(request, response);
    }
}