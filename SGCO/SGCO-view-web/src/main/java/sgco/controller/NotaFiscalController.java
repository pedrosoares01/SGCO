package sgco.controller;

import sgco.model.dao.NotaFiscalDAO;
import sgco.model.dao.OrcamentoDAO;
import sgco.model.domain.NotaFiscal;
import sgco.model.domain.Orcamento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "NotaFiscalController", urlPatterns = {"/NotaFiscalController"})
public class NotaFiscalController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if ("emitir".equals(acao)) {
            emitirNota(request, response);
        } else {
            response.sendRedirect("core/pagamento/pagina.jsp");
        }
    }

    private void emitirNota(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int orcamentoId = Integer.parseInt(request.getParameter("orcamentoId"));

            OrcamentoDAO orcDao = new OrcamentoDAO();
            Orcamento orcamento = orcDao.buscarPorId(orcamentoId);
            request.setAttribute("orcamento", orcamento);

            if (orcamento == null) {
                request.setAttribute("mensagem", "Orçamento não encontrado.");
                request.setAttribute("tipoMensagem", "erro");
            } else {

                NotaFiscal nota = new NotaFiscal();
                nota.setOrcamentoId(orcamentoId);
                nota.setValorTotal(orcamento.getValor());
                nota.setDataEmissao(LocalDate.now());
                nota.setNomePaciente(orcamento.getNomePaciente());
                nota.setNomeProfissional(orcamento.getNomeProfissional());

                NotaFiscalDAO dao = new NotaFiscalDAO();
                boolean sucesso = dao.inserir(nota);

                if (sucesso) {
                    request.setAttribute("mensagem", "Nota fiscal emitida com sucesso!");
                    request.setAttribute("tipoMensagem", "sucesso");
                } else {
                    request.setAttribute("mensagem", "Erro ao salvar nota fiscal.");
                    request.setAttribute("tipoMensagem", "erro");
                }
            }

        } catch (Exception e) {
            request.setAttribute("mensagem", "Erro ao emitir nota fiscal.");
            request.setAttribute("tipoMensagem", "erro");
        }

        request.getRequestDispatcher("/core/pagamento/pagina.jsp")
               .forward(request, response);
    }
}
