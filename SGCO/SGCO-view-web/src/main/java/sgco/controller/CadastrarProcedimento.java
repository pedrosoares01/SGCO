package sgco.controller;

import sgco.dao.exception.NomeProcedimentoInvalidoException;
import sgco.model.dao.ProcedimentoDAO;
import sgco.model.domain.Procedimento;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastrarProcedimento", urlPatterns = {"/CadastrarProcedimento"})
public class CadastrarProcedimento extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🟦 Captura dos parâmetros do formulário
        String nome = request.getParameter("procedimento");
        String precoStr = request.getParameter("preco");

        // 🟥 Validação inicial (campos vazios)
        if (nome == null || nome.trim().isEmpty() || precoStr == null || precoStr.trim().isEmpty()) {
            enviarMensagem(request, response, "Preencha todos os campos!", "erro");
            return;
        }

        try {
            // 🟦 Conversão e criação do objeto
            double preco = Double.parseDouble(precoStr);
            Procedimento p = new Procedimento(nome, preco);

            ProcedimentoDAO dao = new ProcedimentoDAO();
            boolean sucesso = dao.inserir(p);

            // 🟩 Mensagem de sucesso ou erro no banco
            if (sucesso) {
                enviarMensagem(request, response, "Procedimento cadastrado com sucesso!", "sucesso");
            } else {
                enviarMensagem(request, response, "Erro ao cadastrar o procedimento no banco de dados.", "erro");
            }

        } catch (NumberFormatException e) {
            enviarMensagem(request, response, "Preço inválido. Digite apenas números e ponto (ex: 120.50).", "erro");

        } catch (NomeProcedimentoInvalidoException e) {
            enviarMensagem(request, response, e.getMessage(), "erro");

        } catch (Exception e) {
            e.printStackTrace();
            enviarMensagem(request, response, "Erro inesperado: " + e.getMessage(), "erro");
        }
    }

    // 🧩 Método auxiliar para enviar mensagem à página
    private void enviarMensagem(HttpServletRequest request, HttpServletResponse response,
                                String mensagem, String tipo)
            throws ServletException, IOException {

        request.setAttribute("mensagem", mensagem);
        request.setAttribute("tipoMensagem", tipo);
        request.getRequestDispatcher("/core/procedimentos/pagina.jsp").forward(request, response);
    }
}