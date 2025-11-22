package sgco.controller;

import sgco.model.dao.OrcamentoDAO;
import sgco.model.dao.PagamentoDAO;
import sgco.model.domain.Orcamento;
import sgco.model.domain.Pagamento;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "PagamentoController", urlPatterns = {"/PagamentoController"})
public class PagamentoController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(PagamentoController.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        if (acao == null) acao = "";

        logger.log(Level.INFO, "Ação recebida em PagamentoController: {0}", acao);

        switch (acao) {
            case "selecionarOrcamento":
                selecionarOrcamento(request, response);
                break;

            case "salvar":
                salvarPagamento(request, response);
                break;

            default:
                enviarMensagem(request, response, "Ação inválida: " + acao, "erro");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("core/pagamento/pagina.jsp");
    }

    private void selecionarOrcamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("orcamentoId");

        if (idStr == null || idStr.trim().isEmpty()) {
            enviarMensagem(request, response, "ID do orçamento não informado!", "erro");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);

            OrcamentoDAO dao = new OrcamentoDAO();
            Orcamento orc = dao.buscarPorId(id);

            if (orc != null) {
                logger.log(Level.INFO, "Orçamento encontrado: ID {0}", id);
                request.setAttribute("orcamento", orc);

                request.getRequestDispatcher("/core/pagamento/pagina.jsp").forward(request, response);

            } else {
                enviarMensagem(request, response, "Orçamento não encontrado!", "erro");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao selecionar orçamento.", e);
            enviarMensagem(request, response, "Erro ao buscar orçamento: " + e.getMessage(), "erro");
        }
    }

    private void salvarPagamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String idStr = request.getParameter("orcamentoId");
            String forma = request.getParameter("formaPagamento");
            String valorStr = request.getParameter("valor");

            if (idStr == null || forma == null || forma.isEmpty() || valorStr == null) {
                enviarMensagem(request, response, "Preencha todos os campos!", "erro");
                return;
            }

            int orcamentoId = Integer.parseInt(idStr);
            double valor = Double.parseDouble(valorStr);

            Pagamento pagamento = new Pagamento();
            pagamento.setOrcamentoId(orcamentoId);
            pagamento.setFormaPagamento(forma);
            pagamento.setValorPago(valor);
            pagamento.setDataPagamento(LocalDate.now());

            PagamentoDAO dao = new PagamentoDAO();
            boolean sucesso = dao.inserir(pagamento);

            if (sucesso) {
                logger.log(Level.INFO, "Pagamento registrado. Orçamento ID {0}", orcamentoId);
                enviarMensagem(request, response, "Pagamento registrado com sucesso!", "sucesso");
            } else {
                enviarMensagem(request, response, "Erro ao registrar pagamento.", "erro");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao salvar pagamento.", e);
            enviarMensagem(request, response, "Erro ao salvar: " + e.getMessage(), "erro");
        }
    }
    private void enviarMensagem(HttpServletRequest request, HttpServletResponse response,
                                String mensagem, String tipo)
            throws ServletException, IOException {

        request.setAttribute("mensagem", mensagem);
        request.setAttribute("tipoMensagem", tipo);

        request.getRequestDispatcher("/core/pagamento/pagina.jsp").forward(request, response);
    }
}
