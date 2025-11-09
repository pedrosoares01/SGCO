package sgco.controller;

import sgco.model.dao.ProcedimentoDAO;
import sgco.model.dao.PacienteDAO;
import sgco.model.dao.OrcamentoDAO;
import sgco.model.domain.Procedimento;
import sgco.model.domain.Paciente;
import sgco.model.domain.Orcamento;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrcamentoController", urlPatterns = {"/OrcamentoController"})
public class OrcamentoController extends HttpServlet {

    private ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
    private OrcamentoDAO orcamentoDAO = new OrcamentoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if ("selecionarProcedimento".equals(acao)) {
            selecionarProcedimento(request, response);
        } else if ("listarSelecionados".equals(acao)) {
            listarProcedimentosSelecionados(request, response);
        } else if ("selecionarPaciente".equals(acao)) {
            selecionarPaciente(request, response);
        } else {
            response.sendRedirect("core/orcamento/pagina.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if ("salvar".equals(acao)) {
            salvarOrcamento(request, response);
        } else {
            response.sendRedirect("core/orcamento/pagina.jsp");
        }
    }

    private void selecionarProcedimento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int idProcedimento = Integer.parseInt(request.getParameter("id"));
            Procedimento procedimento = procedimentoDAO.buscarPorId(idProcedimento);

            HttpSession session = request.getSession();
            List<Procedimento> procedimentosSelecionados =
                    (List<Procedimento>) session.getAttribute("procedimentosSelecionados");

            if (procedimentosSelecionados == null) {
                procedimentosSelecionados = new ArrayList<>();
            }

            procedimentosSelecionados.add(procedimento);
            session.setAttribute("procedimentosSelecionados", procedimentosSelecionados);

            request.setAttribute("mensagem", "Procedimento adicionado ao orçamento!");
            request.getRequestDispatcher("core/orcamento/pagina.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Erro ao adicionar procedimento: " + e.getMessage());
            request.getRequestDispatcher("core/orcamento/pagina.jsp").forward(request, response);
        }
    }

    private void listarProcedimentosSelecionados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Procedimento> procedimentosSelecionados =
                (List<Procedimento>) session.getAttribute("procedimentosSelecionados");

        if (procedimentosSelecionados == null) {
            procedimentosSelecionados = new ArrayList<>();
        }

        request.setAttribute("procedimentosSelecionados", procedimentosSelecionados);
        request.getRequestDispatcher("core/orcamento/pagina.jsp").forward(request, response);
    }

    private void selecionarPaciente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int idPaciente = Integer.parseInt(request.getParameter("id"));
            PacienteDAO pacienteDAO = new PacienteDAO();
            Paciente paciente = pacienteDAO.buscarPorId(idPaciente);

            HttpSession session = request.getSession();
            session.setAttribute("pacienteSelecionado", paciente);

            request.setAttribute("mensagem", "Paciente selecionado com sucesso!");
            request.getRequestDispatcher("core/orcamento/pagina.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Erro ao selecionar paciente: " + e.getMessage());
            request.getRequestDispatcher("core/orcamento/pagina.jsp").forward(request, response);
        }
    }

    private void salvarOrcamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();

            Paciente paciente = (Paciente) session.getAttribute("pacienteSelecionado");
            List<Procedimento> procedimentosSelecionados =
                    (List<Procedimento>) session.getAttribute("procedimentosSelecionados");

            if (paciente == null) {
                request.setAttribute("mensagemErro", "Selecione um paciente antes de salvar o orçamento.");
                request.getRequestDispatcher("core/orcamento/pagina.jsp").forward(request, response);
                return;
            }

            if (procedimentosSelecionados == null || procedimentosSelecionados.isEmpty()) {
                request.setAttribute("mensagemErro", "Adicione pelo menos um procedimento antes de salvar o orçamento.");
                request.getRequestDispatcher("core/orcamento/pagina.jsp").forward(request, response);
                return;
            }

            Orcamento orcamento = new Orcamento();
            orcamento.setDescricao(request.getParameter("descricao"));
            orcamento.setIdPaciente(paciente.getId());
            orcamento.setIdProfissional(1);
            orcamento.setValor(procedimentosSelecionados.stream().mapToDouble(Procedimento::getPreco).sum());

            orcamentoDAO.inserir(orcamento);

            session.removeAttribute("procedimentosSelecionados");
            session.removeAttribute("pacienteSelecionado");

            request.setAttribute("mensagem", "Orçamento salvo com sucesso!");
            request.getRequestDispatcher("core/orcamento/pagina.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Erro ao salvar orçamento: " + e.getMessage());
            request.getRequestDispatcher("core/orcamento/pagina.jsp").forward(request, response);
        }
    }
}