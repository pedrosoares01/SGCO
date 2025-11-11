package sgco.controller;

import sgco.model.dao.ProcedimentoDAO;
import sgco.model.domain.Procedimento;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ProcedimentoController", urlPatterns = {"/ProcedimentoController"})
public class ProcedimentoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if (acao == null) {
            acao = "pesquisar";
        }

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
                request.setAttribute("mensagem", "Ação inválida.");
                request.setAttribute("tipoMensagem", "erro");
                request.getRequestDispatcher("/core/procedimentos/pagina.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

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
                enviarMensagem(request, response, "Procedimento cadastrado com sucesso!", "sucesso");
            } else {
                enviarMensagem(request, response, "Erro ao cadastrar o procedimento no banco de dados.", "erro");
            }

        } catch (NumberFormatException e) {
            enviarMensagem(request, response, "Preço inválido. Digite apenas números e ponto (ex: 120.50).", "erro");
        } catch (Exception e) {
            e.printStackTrace();
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
                request.setAttribute("procedimento", procedimento);
                request.getRequestDispatcher("/core/procedimentos/editar.jsp").forward(request, response);
            } else {
                enviarMensagem(request, response, "Procedimento não encontrado.", "erro");
            }

        } catch (NumberFormatException e) {
            enviarMensagem(request, response, "ID inválido. Verifique e tente novamente.", "erro");
        } catch (Exception e) {
            e.printStackTrace();
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
                enviarMensagem(request, response, "Procedimento atualizado com sucesso!", "sucesso");
            } else {
                enviarMensagem(request, response, "Erro ao atualizar o procedimento.", "erro");
            }

        } catch (NumberFormatException e) {
            enviarMensagem(request, response, "Formato inválido no ID ou preço.", "erro");
        } catch (Exception e) {
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

    private void pesquisarProcedimento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nomeProcedimento = request.getParameter("searchProcedimento");
        String origem = request.getParameter("origem");

        try {
            ProcedimentoDAO dao = new ProcedimentoDAO();
            List<Procedimento> resultados = dao.pesquisarPorNome(nomeProcedimento);
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
            enviarMensagem(request, response, "Erro ao pesquisar: " + e.getMessage(), "erro");
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
