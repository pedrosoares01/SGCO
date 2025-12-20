package sgco;

import sgco.sgco.service.FornecedorServicoService;
import sgco.sgco.domain.FornecedorServico;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "FornecedorServicoController", urlPatterns = {"/FornecedorServicoController"})
public class FornecedorServicoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }

        switch (action) {
            case "cadastrar":
                cadastrarFornecedorServico(request, response);
                break;
            case "editar":
                editarFornecedorServico(request, response);
                break;
            case "excluir":
                excluirFornecedorServico(request, response);
                break;
            default:
                listarFornecedoresServicos(request, response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "listar";

        switch (action) {
            case "pesquisar":
                pesquisarFornecedorServico(request, response);
                break;
            case "carregarEdicao":
                carregarFornecedorServicoParaEdicao(request, response);
                break;
            default:
                listarFornecedoresServicos(request, response);
                break;
        }
    }

    private void cadastrarFornecedorServico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nomeServico = request.getParameter("nomeServico");
            String fornecedor = request.getParameter("fornecedor");
            String contato = request.getParameter("contato");

            FornecedorServico fornecedorServico = new FornecedorServico(nomeServico, fornecedor, contato);
            FornecedorServicoService service = new FornecedorServicoService();
            service.cadastrarFornecedorServico(fornecedorServico);

            listarFornecedoresServicos(request, response);
            request.setAttribute("msg", "Cadastro realizado com sucesso!");
            request.setAttribute("tipoMensagem", "sucesso");
            request.getRequestDispatcher("fornecedores_servicos/fornecedores_servicos.jsp").forward(request, response);

        } catch(Exception e){
            listarFornecedoresServicos(request, response);
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("fornecedores_servicos/fornecedores_servicos.jsp").forward(request, response);
        }
    }

    private void carregarFornecedorServicoParaEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nomeServico = request.getParameter("nomeServico");
            String fornecedor = request.getParameter("fornecedor");

            FornecedorServicoService service = new FornecedorServicoService();
            FornecedorServico fornecedorServico = service.buscarFornecedorServico(nomeServico, fornecedor);

            if (fornecedorServico != null) {
                request.setAttribute("fornecedorServicoEdicao", fornecedorServico);
                request.setAttribute("modoEdicao", true);
                request.setAttribute("nomeServicoOriginal", nomeServico);
                request.setAttribute("fornecedorOriginal", fornecedor);
                request.setAttribute("msg", "Serviço carregado para edição com sucesso!");
                request.setAttribute("tipoMensagem", "sucesso");
            } else {
                request.setAttribute("msg", "Serviço não encontrado para edição.");
                request.setAttribute("tipoMensagem", "erro");
            }

            listarFornecedoresServicos(request, response);
            request.getRequestDispatcher("fornecedores_servicos/fornecedores_servicos.jsp").forward(request, response);

        } catch (Exception e) {
            listarFornecedoresServicos(request, response);
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("fornecedores_servicos/fornecedores_servicos.jsp").forward(request, response);
        }
    }

    private void editarFornecedorServico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nomeServicoOriginal = request.getParameter("nomeServicoOriginal");
            String fornecedorOriginal = request.getParameter("fornecedorOriginal");
            String novoNomeServico = request.getParameter("nomeServico");
            String novoFornecedor = request.getParameter("fornecedor");
            String contato = request.getParameter("contato");

            FornecedorServico fornecedorServico = new FornecedorServico(novoNomeServico, novoFornecedor, contato);
            FornecedorServicoService service = new FornecedorServicoService();
            service.atualizarFornecedorServico(nomeServicoOriginal, fornecedorOriginal, fornecedorServico);

            request.setAttribute("modoEdicao", false);
            listarFornecedoresServicos(request, response);
            request.setAttribute("msg", "Edição realizada com sucesso!");
            request.setAttribute("tipoMensagem", "sucesso");
            request.getRequestDispatcher("fornecedores_servicos/fornecedores_servicos.jsp").forward(request, response);

        } catch (Exception e) {
            listarFornecedoresServicos(request, response);
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("fornecedores_servicos/fornecedores_servicos.jsp").forward(request, response);
        }
    }

    private void excluirFornecedorServico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nomeServico = request.getParameter("nomeServico");
            String fornecedor = request.getParameter("fornecedor");

            FornecedorServicoService service = new FornecedorServicoService();
            service.excluirFornecedorServico(nomeServico, fornecedor);

            listarFornecedoresServicos(request, response);
            request.setAttribute("msg", "Exclusão realizada com sucesso!");
            request.setAttribute("tipoMensagem", "sucesso");
            request.getRequestDispatcher("fornecedores_servicos/fornecedores_servicos.jsp").forward(request, response);

        } catch (Exception e) {
            listarFornecedoresServicos(request, response);
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("fornecedores_servicos/fornecedores_servicos.jsp").forward(request, response);
        }
    }

    private void pesquisarFornecedorServico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nomeServico = request.getParameter("searchServico");
            String fornecedor = request.getParameter("searchFornecedor");

            FornecedorServicoService service = new FornecedorServicoService();
            List<FornecedorServico> resultados = service.pesquisarFornecedoresServicos(nomeServico, fornecedor);

            if (resultados.isEmpty()) {
                request.setAttribute("msg", "Nenhum serviço encontrado.");
                request.setAttribute("tipoMensagem", "alerta");
            } else {
                request.setAttribute("msg", "Pesquisa realizada com sucesso!");
                request.setAttribute("tipoMensagem", "sucesso");
            }

            request.setAttribute("fornecedoresServicos", resultados);
            request.setAttribute("searchServico", nomeServico != null ? nomeServico : "");
            request.setAttribute("searchFornecedor", fornecedor != null ? fornecedor : "");

            request.getRequestDispatcher("fornecedores_servicos/fornecedores_servicos.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("fornecedores_servicos/fornecedores_servicos.jsp").forward(request, response);
        }
    }

    private void listarFornecedoresServicos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            FornecedorServicoService service = new FornecedorServicoService();
            List<FornecedorServico> todos = service.listarTodosFornecedoresServicos();

            request.setAttribute("fornecedoresServicos", todos);
            request.setAttribute("msg", "Listagem realizada com sucesso!");
            request.setAttribute("tipoMensagem", "sucesso");

            String mensagem = request.getParameter("mensagem");
            String tipoMensagem = request.getParameter("tipoMensagem");
            if (mensagem != null && tipoMensagem != null) {
                request.setAttribute("msg", mensagem);
                request.setAttribute("tipoMensagem", tipoMensagem);
            }

            request.getRequestDispatcher("fornecedores_servicos/fornecedores_servicos.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("fornecedores_servicos/fornecedores_servicos.jsp").forward(request, response);
        }
    }
}