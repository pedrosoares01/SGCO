package sgco.controller;

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

        if ("cadastrar".equals(action)) {
            cadastrarFornecedorServico(request, response);
        } else if ("editar".equals(action)) {
            editarFornecedorServico(request, response);
        } else if ("excluir".equals(action)) {
            excluirFornecedorServico(request, response);
        } else {
            listarFornecedoresServicos(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("pesquisar".equals(action)) {
            pesquisarFornecedorServico(request, response);
        } else if ("carregarEdicao".equals(action)) {
            carregarFornecedorServicoParaEdicao(request, response);
        } else {
            listarFornecedoresServicos(request, response);
        }
    }

    private void cadastrarFornecedorServico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nomeServico = request.getParameter("nomeServico");
        String fornecedor = request.getParameter("fornecedor");
        String contato = request.getParameter("contato");

        String mensagem;
        String tipoMensagem;

        if (nomeServico == null || nomeServico.trim().isEmpty()) {
            mensagem = "Erro: Nome do serviço é obrigatório.";
            tipoMensagem = "erro";
        } else if (fornecedor == null || fornecedor.trim().isEmpty()) {
            mensagem = "Erro: Nome do fornecedor é obrigatório.";
            tipoMensagem = "erro";
        } else {
            try {
                FornecedorServico fornecedorServico = new FornecedorServico(nomeServico, fornecedor, contato);
                FornecedorServicoService service = new FornecedorServicoService();

                if (service.cadastrarFornecedorServico(fornecedorServico)) {
                    mensagem = "Serviço cadastrado com sucesso!";
                    tipoMensagem = "sucesso";
                } else {
                    mensagem = "Falha ao cadastrar o serviço.";
                    tipoMensagem = "erro";
                }
            } catch (Exception e) {
                mensagem = "Erro: " + e.getMessage();
                tipoMensagem = "erro";
            }
        }

        request.setAttribute("mensagem", mensagem);
        request.setAttribute("tipoMensagem", tipoMensagem);
        listarFornecedoresServicos(request, response);
    }

    private void carregarFornecedorServicoParaEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nomeServico = request.getParameter("nomeServico");
        String fornecedor = request.getParameter("fornecedor");

        try {
            FornecedorServicoService service = new FornecedorServicoService();
            FornecedorServico fornecedorServico = service.buscarFornecedorServico(nomeServico, fornecedor);

            if (fornecedorServico != null) {
                request.setAttribute("fornecedorServicoEdicao", fornecedorServico);
                request.setAttribute("modoEdicao", true);
                // Guardar os valores originais para a edição
                request.setAttribute("nomeServicoOriginal", nomeServico);
                request.setAttribute("fornecedorOriginal", fornecedor);
            } else {
                request.setAttribute("mensagem", "Serviço não encontrado para edição.");
                request.setAttribute("tipoMensagem", "erro");
            }

            listarFornecedoresServicos(request, response);

        } catch (Exception e) {
            request.setAttribute("mensagem", "Erro ao carregar serviço para edição: " + e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            listarFornecedoresServicos(request, response);
        }
    }

    private void editarFornecedorServico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nomeServicoOriginal = request.getParameter("nomeServicoOriginal");
        String fornecedorOriginal = request.getParameter("fornecedorOriginal");
        String novoNomeServico = request.getParameter("nomeServico");
        String novoFornecedor = request.getParameter("fornecedor");
        String contato = request.getParameter("contato");

        String mensagem;
        String tipoMensagem;

        if (novoNomeServico == null || novoNomeServico.trim().isEmpty()) {
            mensagem = "Erro: Nome do serviço é obrigatório.";
            tipoMensagem = "erro";
        } else if (novoFornecedor == null || novoFornecedor.trim().isEmpty()) {
            mensagem = "Erro: Nome do fornecedor é obrigatório.";
            tipoMensagem = "erro";
        } else if (nomeServicoOriginal == null || fornecedorOriginal == null) {
            mensagem = "Erro: Dados originais não encontrados para edição.";
            tipoMensagem = "erro";
        } else {
            try {
                FornecedorServico fornecedorServico = new FornecedorServico(novoNomeServico, novoFornecedor, contato);
                FornecedorServicoService service = new FornecedorServicoService();

                if (service.atualizarFornecedorServico(nomeServicoOriginal, fornecedorOriginal, fornecedorServico)) {
                    mensagem = "Serviço atualizado com sucesso!";
                    tipoMensagem = "sucesso";
                } else {
                    mensagem = "Falha ao atualizar o serviço.";
                    tipoMensagem = "erro";
                }
            } catch (Exception e) {
                mensagem = "Erro: " + e.getMessage();
                tipoMensagem = "erro";
            }
        }

        request.setAttribute("mensagem", mensagem);
        request.setAttribute("tipoMensagem", tipoMensagem);
        request.setAttribute("modoEdicao", false);
        listarFornecedoresServicos(request, response);
    }

    private void excluirFornecedorServico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nomeServico = request.getParameter("nomeServico");
        String fornecedor = request.getParameter("fornecedor");

        String mensagem;
        String tipoMensagem;

        if (nomeServico == null || nomeServico.trim().isEmpty() ||
                fornecedor == null || fornecedor.trim().isEmpty()) {
            mensagem = "Erro: Nome do serviço e fornecedor são obrigatórios.";
            tipoMensagem = "erro";
        } else {
            try {
                FornecedorServicoService service = new FornecedorServicoService();

                if (service.excluirFornecedorServico(nomeServico, fornecedor)) {
                    mensagem = "Serviço '" + nomeServico + "' do fornecedor '" + fornecedor + "' excluído com sucesso!";
                    tipoMensagem = "sucesso";
                } else {
                    mensagem = "Falha ao excluir o serviço.";
                    tipoMensagem = "erro";
                }
            } catch (Exception e) {
                mensagem = "Erro ao excluir serviço: " + e.getMessage();
                tipoMensagem = "erro";
            }
        }

        request.setAttribute("mensagem", mensagem);
        request.setAttribute("tipoMensagem", tipoMensagem);
        listarFornecedoresServicos(request, response);
    }

    private void pesquisarFornecedorServico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nomeServico = request.getParameter("searchServico");
        String fornecedor = request.getParameter("searchFornecedor");

        try {
            FornecedorServicoService service = new FornecedorServicoService();
            List<FornecedorServico> resultados = service.pesquisarFornecedoresServicos(nomeServico, fornecedor);

            if (resultados.isEmpty()) {
                request.setAttribute("mensagem", "Nenhum serviço encontrado.");
                request.setAttribute("tipoMensagem", "alerta");
            } else {
                request.setAttribute("mensagem", "Resultados encontrados: " + resultados.size());
                request.setAttribute("tipoMensagem", "sucesso");
            }

            request.setAttribute("fornecedoresServicos", resultados);
            request.setAttribute("searchServico", nomeServico != null ? nomeServico : "");
            request.setAttribute("searchFornecedor", fornecedor != null ? fornecedor : "");

            request.getRequestDispatcher("fornecedores_servicos.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("mensagem", "Erro ao pesquisar serviços: " + e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            listarFornecedoresServicos(request, response);
        }
    }

    private void listarFornecedoresServicos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            FornecedorServicoService service = new FornecedorServicoService();
            List<FornecedorServico> todos = service.listarTodosFornecedoresServicos();

            request.setAttribute("fornecedoresServicos", todos);

            // Manter mensagem se existir
            String mensagem = request.getParameter("mensagem");
            String tipoMensagem = request.getParameter("tipoMensagem");
            if (mensagem != null && tipoMensagem != null) {
                request.setAttribute("mensagem", mensagem);
                request.setAttribute("tipoMensagem", tipoMensagem);
            }

            request.getRequestDispatcher("fornecedores_servicos.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("mensagem", "Erro ao carregar serviços: " + e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("fornecedores_servicos/fornecedores_servicos.jsp").forward(request, response);
        }
    }
}