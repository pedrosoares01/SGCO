package sgco.controller;

import sgco.model.dao.PacienteDAO;
import sgco.model.domain.Paciente;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "PacienteController", urlPatterns = {"/PacienteController"})
public class PacienteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if (acao == null) {
            acao = "pesquisar";
        }

        switch (acao) {
            case "cadastrar":
                cadastrarPaciente(request, response);
                break;
            case "editar":
                editarPaciente(request, response);
                break;
            case "atualizar":
                atualizarPaciente(request, response);
                break;
            case "excluir":
                excluirPaciente(request, response);
                break;
            case "pesquisar":
                pesquisarPaciente(request, response);
                break;
            default:
                enviarMensagem(request, response, "Ação inválida.", "erro");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if ("excluir".equals(acao)) {
            excluirPaciente(request, response);
        } else if ("editar".equals(acao)) {
            editarPaciente(request, response);
        } else {
            response.sendRedirect("core/paciente/pagina.jsp");
        }
    }

    private void cadastrarPaciente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");

        if (nome == null || nome.isEmpty() || cpf == null || cpf.isEmpty()) {
            enviarMensagem(request, response, "Nome e CPF são obrigatórios.", "erro");
            return;
        }

        try {
            Paciente p = new Paciente(nome, cpf, endereco, telefone, email);
            PacienteDAO dao = new PacienteDAO();

            boolean sucesso = dao.inserir(p);

            if (sucesso) {
                response.sendRedirect("PacienteController?acao=pesquisar");
            } else {
                enviarMensagem(request, response, "Erro ao cadastrar o paciente.", "erro");
            }

        } catch (Exception e) {
            enviarMensagem(request, response, "Erro inesperado: " + e.getMessage(), "erro");
        }
    }

    private void editarPaciente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            enviarMensagem(request, response, "ID não informado.", "erro");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            PacienteDAO dao = new PacienteDAO();
            Paciente paciente = dao.buscarPorId(id);

            if (paciente != null) {
                request.setAttribute("paciente", paciente);
                request.getRequestDispatcher("/core/paciente/editar.jsp").forward(request, response);
            } else {
                enviarMensagem(request, response, "Paciente não encontrado.", "erro");
            }

        } catch (Exception e) {
            enviarMensagem(request, response, "Erro ao carregar paciente: " + e.getMessage(), "erro");
        }
    }

    private void atualizarPaciente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");

        if (idStr == null || idStr.isEmpty()) {
            enviarMensagem(request, response, "ID inválido.", "erro");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);

            Paciente p = new Paciente(id, nome, cpf, endereco, telefone, email);

            PacienteDAO dao = new PacienteDAO();
            boolean sucesso = dao.atualizar(p);

            if (sucesso) {
                enviarMensagem(request, response, "Paciente atualizado com sucesso!", "sucesso");
            } else {
                enviarMensagem(request, response, "Erro ao atualizar paciente.", "erro");
            }

        } catch (Exception e) {
            enviarMensagem(request, response, "Erro inesperado: " + e.getMessage(), "erro");
        }
    }

    private void excluirPaciente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            enviarMensagem(request, response, "ID não informado.", "erro");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            PacienteDAO dao = new PacienteDAO();
            boolean sucesso = dao.excluir(id);

            if (sucesso) {
                enviarMensagem(request, response, "Paciente excluído com sucesso!", "sucesso");
            } else {
                enviarMensagem(request, response, "Erro ao excluir paciente.", "erro");
            }

        } catch (Exception e) {
            enviarMensagem(request, response, "Erro ao excluir: " + e.getMessage(), "erro");
        }
    }

    private void pesquisarPaciente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("search");
        String origem = request.getParameter("origem");

        System.out.println(">>> DEBUG PacienteController.pesquisarPaciente()");
        System.out.println("search = " + nome);
        System.out.println("origem = " + origem);
        System.out.println("acao = " + request.getParameter("acao"));
        System.out.println("--------------------------------------");

        try {
            PacienteDAO dao = new PacienteDAO();
            List<Paciente> resultados = dao.pesquisarPorNome(nome);

            request.setAttribute("resultadosPacientes", resultados);

            if ("orcamento".equals(origem)) {
                Integer idProcedimento = (Integer) request.getSession().getAttribute("idProcedimentoSelecionado");
                if (idProcedimento != null) {
                    sgco.model.dao.ProcedimentoDAO procDao = new sgco.model.dao.ProcedimentoDAO();
                    sgco.model.domain.Procedimento procedimento = procDao.buscarPorId(idProcedimento);
                    request.setAttribute("procedimentoSelecionado", procedimento);
                }

                request.getRequestDispatcher("/core/orcamento/novo_orcamento.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/core/paciente/pagina.jsp").forward(request, response);
            }

        } catch (Exception e) {
            enviarMensagem(request, response, "Erro ao pesquisar pacientes: " + e.getMessage(), "erro");
        }
    }

    private void enviarMensagem(HttpServletRequest request, HttpServletResponse response,
                                String mensagem, String tipo)
            throws ServletException, IOException {

        request.setAttribute("mensagem", mensagem);
        request.setAttribute("tipoMensagem", tipo);
        request.getRequestDispatcher("/core/paciente/pagina.jsp").forward(request, response);
    }
}
