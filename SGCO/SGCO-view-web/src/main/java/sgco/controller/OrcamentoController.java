package sgco.controller;

import sgco.model.dao.OrcamentoDAO;
import sgco.model.dao.PacienteDAO;
import sgco.model.dao.ProcedimentoDAO;
import sgco.model.domain.Orcamento;
import sgco.model.domain.Paciente;
import sgco.model.domain.Procedimento;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

@WebServlet(name = "OrcamentoController", urlPatterns = {"/OrcamentoController"})
public class OrcamentoController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(OrcamentoController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        try {
            if (acao == null || acao.equals("listar")) {
                listarOrcamentos(request, response);
            } else if (acao.equals("editar")) {
                editarOrcamento(request, response);
            } else if (acao.equals("pesquisar")) {
                pesquisarPorId(request, response);
            } else if (acao.equals("selecionarProcedimento")) {
                selecionarProcedimento(request, response);
            } else if (acao.equals("novo")) {
                abrirNovoOrcamento(request, response);
            } else if (acao.equals("excluir")) {
                excluirOrcamento(request, response);
            }
        } catch (Exception e) {
            logger.severe("Erro em doGet: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Erro: " + e.getMessage());
            request.getRequestDispatcher("core/orcamento/pagina.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        try {
            if ("salvar".equals(acao) || "inserir".equals(acao)) {
                salvarOrcamento(request, response);
            } else if ("atualizar".equals(acao)) {
                atualizarOrcamento(request, response);
            } else if ("selecionarPaciente".equals(acao)) {
                selecionarPaciente(request, response);
            } else if ("pesquisar".equals(acao)) {
                pesquisarPorId(request, response);
            } else {
                listarOrcamentos(request, response);
            }
        } catch (Exception e) {
            logger.severe("Erro em doPost: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Erro ao processar: " + e.getMessage());
            request.getRequestDispatcher("core/orcamento/pagina.jsp").forward(request, response);
        }
    }

    private void listarOrcamentos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        OrcamentoDAO dao = new OrcamentoDAO();
        List<Orcamento> lista = dao.listarTodos();
        request.setAttribute("resultados", lista);
        request.getRequestDispatcher("core/orcamento/pagina.jsp").forward(request, response);
    }

    private void pesquisarPorId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String idParam = request.getParameter("searchId");
        List<Orcamento> resultados;
        OrcamentoDAO dao = new OrcamentoDAO();
        String origem = request.getParameter("origem");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Orcamento o = dao.buscarPorId(id);
                resultados = (o != null) ? List.of(o) : List.of();
            } catch (NumberFormatException e) {
                resultados = List.of();
            }
        } else {
            resultados = List.of();
        }

        request.setAttribute("resultados", resultados);
        if ("pagamento".equals(origem)) {
       	 request.getRequestDispatcher("/core/pagamento/pagina.jsp").forward(request, response);
        } else {
        	request.getRequestDispatcher("core/orcamento/pagina.jsp").forward(request, response);
        }
        
    }

    private void editarOrcamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        OrcamentoDAO dao = new OrcamentoDAO();
        Orcamento orcamento = dao.buscarPorId(id);

        if (orcamento != null) {
            request.setAttribute("orcamento", orcamento);
            request.getRequestDispatcher("/core/orcamento/editar_orcamento.jsp").forward(request, response);
        } else {
            request.setAttribute("mensagem", "Orçamento não encontrado!");
            request.getRequestDispatcher("/core/orcamento/pagina.jsp").forward(request, response);
        }
    }

    private void salvarOrcamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        HttpSession session = request.getSession();
        List<Integer> procedimentosSelecionados = (List<Integer>) session.getAttribute("procedimentosSelecionados");

        if (procedimentosSelecionados == null || procedimentosSelecionados.isEmpty()) {
            request.setAttribute("mensagemErro", "Nenhum procedimento selecionado.");
            listarOrcamentos(request, response);
            return;
        }

        int idPaciente = Integer.parseInt(request.getParameter("id_paciente"));
        String valorStr = request.getParameter("valor").replace(",", ".");
        double valor = Double.parseDouble(valorStr);
        Integer idProfissional = (Integer) session.getAttribute("idProfissionalLogado");
        if (idProfissional == null) idProfissional = 1;

        ProcedimentoDAO pDao = new ProcedimentoDAO();
        List<String> nomesProcedimentos = new ArrayList<>();

        for (Integer id : procedimentosSelecionados) {
            Procedimento p = pDao.buscarPorId(id);
            if (p != null) nomesProcedimentos.add(p.getNome());
        }

        Orcamento orc = new Orcamento();
        orc.setIdPaciente(idPaciente);
        orc.setValor(valor);
        orc.setIdProfissional(idProfissional);
        orc.setIdsProcedimentos(procedimentosSelecionados);
        orc.gerarDescricao(nomesProcedimentos);

        OrcamentoDAO dao = new OrcamentoDAO();
        boolean sucesso = dao.inserir(orc);

        if (sucesso) {
            session.removeAttribute("procedimentosSelecionados");
            request.setAttribute("mensagem", "Orçamento salvo com sucesso!");
        } else {
            request.setAttribute("mensagemErro", "Erro ao salvar orçamento.");
        }

        listarOrcamentos(request, response);
    }

    private void atualizarOrcamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String descricao = request.getParameter("descricao");
        String valorStr = request.getParameter("valor").replace(",", ".");
        double valor = Double.parseDouble(valorStr);
        int idProfissional = Integer.parseInt(request.getParameter("idProfissional"));
        int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));

        Orcamento orc = new Orcamento();
        orc.setId(id);
        orc.setDescricao(descricao);
        orc.setValor(valor);
        orc.setIdProfissional(idProfissional);
        orc.setIdPaciente(idPaciente);

        OrcamentoDAO dao = new OrcamentoDAO();

        dao.excluir(id);
        boolean sucesso = dao.inserir(orc); 

        if (sucesso) {
            request.setAttribute("mensagem", "Orçamento atualizado com sucesso!");
        } else {
            request.setAttribute("mensagemErro", "Erro ao atualizar orçamento.");
        }

        listarOrcamentos(request, response);
    }

    private void excluirOrcamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        OrcamentoDAO dao = new OrcamentoDAO();
        boolean sucesso = dao.excluir(id);

        if (sucesso) {
            request.setAttribute("mensagem", "Orçamento excluído com sucesso!");
        } else {
            request.setAttribute("mensagemErro", "Erro ao excluir orçamento.");
        }

        listarOrcamentos(request, response);
    }

    private void selecionarPaciente(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int idPaciente = Integer.parseInt(request.getParameter("id_paciente"));
        request.getSession().setAttribute("idPacienteSelecionado", idPaciente);
        response.sendRedirect("OrcamentoController?acao=novo");
    }

    private void selecionarProcedimento(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        List<Integer> procedimentosSelecionados = (List<Integer>) session.getAttribute("procedimentosSelecionados");

        if (procedimentosSelecionados == null) procedimentosSelecionados = new ArrayList<>();

        int idProcedimento = Integer.parseInt(request.getParameter("id_procedimento"));
        if (!procedimentosSelecionados.contains(idProcedimento)) procedimentosSelecionados.add(idProcedimento);

        session.setAttribute("procedimentosSelecionados", procedimentosSelecionados);
        response.sendRedirect("OrcamentoController?acao=novo");
    }

    private void abrirNovoOrcamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        HttpSession session = request.getSession();
        Integer idPaciente = (Integer) session.getAttribute("idPacienteSelecionado");
        List<Integer> procedimentosSelecionados = (List<Integer>) session.getAttribute("procedimentosSelecionados");

        if (idPaciente != null) {
            PacienteDAO pDao = new PacienteDAO();
            Paciente paciente = pDao.buscarPorId(idPaciente);
            request.setAttribute("pacienteSelecionado", paciente);
        }

        if (procedimentosSelecionados != null && !procedimentosSelecionados.isEmpty()) {
            ProcedimentoDAO prDao = new ProcedimentoDAO();
            List<Procedimento> lista = new ArrayList<>();
            for (Integer id : procedimentosSelecionados) {
                Procedimento p = prDao.buscarPorId(id);
                if (p != null) lista.add(p);
            }
            request.setAttribute("procedimentosSelecionados", lista);
        }

        request.getRequestDispatcher("core/orcamento/novo_orcamento.jsp").forward(request, response);
    }
}
