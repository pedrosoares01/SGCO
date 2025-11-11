package sgco.controller;

import sgco.model.dao.ProcedimentoDAO;
import sgco.model.domain.Procedimento;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ProcedimentoController", urlPatterns = {"/ProcedimentoController"})
public class ProcedimentoController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ProcedimentoController.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        if (acao == null) {
            acao = "pesquisar";
        }

        logger.log(Level.INFO, "Ação recebida: {0}", acao);

        switch (acao) {
            case "cadastrar":
                cadastrarProcedimento(request, response);
                break;
            case "editar":
                editarProcedimento(request, response);
                break;
            case "atualizar":
                atualizarProcedimento(request, response);
                break;
            case "excluir":
                excluirProcedimento(request, response);
                break;
            case "pesquisar":
                pesquisarProcedimento(request, response);
                break;
            default:
                logger.warning("Ação inválida recebida: " + acao);
                request.setAttribute("mensagem", "Ação inválida.");
                request.setAttribute("tipoMensagem", "erro");
                request.getRequestDispatcher("/core/procedimentos/pagina.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        logger.log(Level.INFO, "Requisição GET recebida com ação: {0}", acao);

        if ("excluir".equals(acao)) {
            excluirProcedimento(request, response);
        } else if ("editar".equals(acao)) {
            editarProcedimento(request, response);
        } else {
            response.sendRedirect("core/procedimentos/pagina.jsp");
        }
    }

    private void cadastrarProcedimento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("procedimento");
        String precoStr = request.getParameter("preco");

        if (nome == null || nome.trim().isEmpty() || precoStr == null || precoStr.trim().isEmpty()) {
            enviarMensagem(request, response, "Preencha todos os campos!", "erro");
            return;
        }

        try {
            double preco = Double.parseDouble(precoStr);
            Procedimento p = new Procedimento(nome, preco);
            ProcedimentoDAO dao = new ProcedimentoDAO();

            boolean sucesso = dao.inserir(p);
            if (sucesso) {
                logger.info("Procedimento cadastrado com sucesso: " + nome);
                enviarMensagem(request, response, "Procedimento cadastrado com sucesso!", "sucesso");
            } else {
                logger.warning("Falha ao cadastrar procedimento: " + nome);
                enviarMensagem(request, response, "Erro ao cadastrar o procedimento no banco de dados.", "erro");
            }

        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Preço inválido informado: {0}", precoStr);
            enviarMensagem(request, response, "Preço inválido. Digite apenas números e ponto (ex: 120.50).", "erro");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro inesperado ao cadastrar procedimento.", e);
            enviarMensagem(request, response, "Erro inesperado: " + e.getMessage(), "erro");
        }
    }

    private void editarProcedimento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            enviarMensagem(request, response, "ID do procedimento não informado!", "erro");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            ProcedimentoDAO dao = new ProcedimentoDAO();
            Procedimento procedimento = dao.buscarPorId(id);

            if (procedimento != null) {
                logger.log(Level.INFO, "Procedimento carregado para edição: ID {0}", id);
                request.setAttribute("procedimento", procedimento);
                request.getRequestDispatcher("/core/procedimentos/editar.jsp").forward(request, response);
            } else {
                logger.log(Level.WARNING, "Procedimento não encontrado: ID {0}", id);
                enviarMensagem(request, response, "Procedimento não encontrado.", "erro");
            }

        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "ID inválido informado: {0}", idStr);
            enviarMensagem(request, response, "ID inválido. Verifique e tente novamente.", "erro");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao carregar o procedimento para edição.", e);
            enviarMensagem(request, response, "Erro ao carregar o procedimento: " + e.getMessage(), "erro");
        }
    }

    private void atualizarProcedimento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        String nome = request.getParameter("nome");
        String precoStr = request.getParameter("preco");

        if (idStr == null || nome == null || precoStr == null ||
            idStr.isEmpty() || nome.isEmpty() || precoStr.isEmpty()) {

            enviarMensagem(request, response, "Todos os campos são obrigatórios.", "erro");
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
                logger.log(Level.INFO, "Procedimento atualizado: ID {0}, Nome: {1}", new Object[]{id, nome});
                enviarMensagem(request, response, "Procedimento atualizado com sucesso!", "sucesso");
            } else {
                logger.log(Level.WARNING, "Erro ao atualizar procedimento: ID {0}", id);
                enviarMensagem(request, response, "Erro ao atualizar o procedimento.", "erro");
            }

        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Formato inválido em ID ou preço: {0}", e.getMessage());
            enviarMensagem(request, response, "Formato inválido no ID ou preço.", "erro");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro inesperado ao atualizar procedimento.", e);
            enviarMensagem(request, response, "Erro inesperado: " + e.getMessage(), "erro");
        }
    }

    private void excluirProcedimento(HttpServletRequest request, HttpServletResponse response)
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
                logger.log(Level.INFO, "Procedimento excluído com sucesso: ID {0}", id);
                enviarMensagem(request, response, "Procedimento excluído com sucesso!", "sucesso");
            } else {
                logger.log(Level.WARNING, "Falha ao excluir procedimento: ID {0}", id);
                enviarMensagem(request, response, "Erro ao excluir o procedimento.", "erro");
            }

        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "ID inválido informado: {0}", idStr);
            enviarMensagem(request, response, "ID inválido.", "erro");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao excluir procedimento.", e);
            enviarMensagem(request, response, "Erro ao excluir: " + e.getMessage(), "erro");
        }
    }

    private void pesquisarProcedimento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nomeProcedimento = request.getParameter("searchProcedimento");
        String origem = request.getParameter("origem");

        try {
            ProcedimentoDAO dao = new ProcedimentoDAO();
            List<Procedimento> resultados = dao.pesquisarPorNome(nomeProcedimento);
            logger.log(Level.INFO, "Pesquisa de procedimentos realizada. Termo: {0}, Resultados: {1}",
                    new Object[]{nomeProcedimento, resultados.size()});

            request.setAttribute("resultados", resultados);

            if ("orcamento".equals(origem)) {
                Integer idPaciente = (Integer) request.getSession().getAttribute("idPacienteSelecionado");
                if (idPaciente != null) {
                    sgco.model.dao.PacienteDAO pDao = new sgco.model.dao.PacienteDAO();
                    sgco.model.domain.Paciente paciente = pDao.buscarPorId(idPaciente);
                    request.setAttribute("pacienteSelecionado", paciente);
                }

                request.getRequestDispatcher("/core/orcamento/novo_orcamento.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/core/procedimentos/pagina.jsp").forward(request, response);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao pesquisar procedimentos.", e);
            enviarMensagem(request, response, "Erro ao pesquisar: " + e.getMessage(), "erro");
        }
    }

    private void enviarMensagem(HttpServletRequest request, HttpServletResponse response,
                                String mensagem, String tipo)
            throws ServletException, IOException {

        logger.log(Level.INFO, "Mensagem enviada: {0} - Tipo: {1}", new Object[]{mensagem, tipo});
        request.setAttribute("mensagem", mensagem);
        request.setAttribute("tipoMensagem", tipo);
        request.getRequestDispatcher("/core/procedimentos/pagina.jsp").forward(request, response);
    }
}
