package sgco.controller;


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

        String nome = request.getParameter("procedimento");
        String precoStr = request.getParameter("preco");

        if (nome == null || precoStr == null || nome.isEmpty() || precoStr.isEmpty()) {
            request.setAttribute("mensagem", "Preencha todos os campos!");
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("/core/procedimentos/pagina.jsp").forward(request, response); 
            return;
        }

        try {
            double preco = Double.parseDouble(precoStr);
            Procedimento p = new Procedimento(nome, preco);

            ProcedimentoDAO dao = new ProcedimentoDAO();
            boolean sucesso = dao.inserir(p);

            if (sucesso) {
                request.setAttribute("mensagem", "Procedimento cadastrado com sucesso!");
                request.setAttribute("tipoMensagem", "sucesso");
            } else {
                request.setAttribute("mensagem", "Erro ao cadastrar no banco.");
                request.setAttribute("tipoMensagem", "erro");
            }

            request.getRequestDispatcher("/core/procedimentos/pagina.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Preço inválido.");
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("/core/procedimentos/pagina.jsp").forward(request, response); 
        } catch (Exception e) {
            request.setAttribute("mensagem", "Erro inesperado: " + e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("/core/procedimentos/pagina.jsp").forward(request, response); 
        }
    }
}