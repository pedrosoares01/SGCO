package sgco.controller;

import sgco.sgco.service.FornecedorMaterialService;
import sgco.sgco.domain.FornecedorMaterial;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "FornecedorMaterialController", urlPatterns = {"/FornecedorMaterialController"})
public class FornecedorMaterialController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("cadastrar".equals(action)) {
            cadastrarFornecedor(request, response);
        } else if ("editar".equals(action)) {
            editarFornecedor(request, response);
        } else if ("excluir".equals(action)) {
            excluirFornecedor(request, response);
        } else {
            listarFornecedores(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("pesquisar".equals(action)) {
            pesquisarFornecedor(request, response);
        } else if ("carregarEdicao".equals(action)) {
            carregarFornecedorParaEdicao(request, response);
        } else {
            listarFornecedores(request, response);
        }
    }

    private void cadastrarFornecedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nomeFornecedor");
        String contato = request.getParameter("contatoFornecedor");
        String email = request.getParameter("emailFornecedor");

        String mensagem;
        String tipoMensagem;

        if (nome == null || nome.trim().isEmpty()) {
            mensagem = "Erro: Nome do fornecedor é obrigatório.";
            tipoMensagem = "erro";
        } else {
            try {
                FornecedorMaterial fornecedor = new FornecedorMaterial(nome, contato, email);
                FornecedorMaterialService service = new FornecedorMaterialService();

                if (service.cadastrarFornecedor(fornecedor)) {
                    mensagem = "Fornecedor cadastrado com sucesso!";
                    tipoMensagem = "sucesso";
                } else {
                    mensagem = "Falha ao cadastrar o fornecedor.";
                    tipoMensagem = "erro";
                }
            } catch (Exception e) {
                mensagem = "Erro de sistema ao cadastrar fornecedor: " + e.getMessage();
                tipoMensagem = "erro";
            }
        }

        request.setAttribute("mensagem", mensagem);
        request.setAttribute("tipoMensagem", tipoMensagem);
        listarFornecedores(request, response);
    }

    private void carregarFornecedorParaEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");

        try {
            FornecedorMaterialService service = new FornecedorMaterialService();
            FornecedorMaterial fornecedor = service.buscarFornecedor(nome);

            if (fornecedor != null) {
                request.setAttribute("fornecedorEdicao", fornecedor);
                request.setAttribute("modoEdicao", true);
                request.setAttribute("nomeOriginal", nome);
            } else {
                request.setAttribute("mensagem", "Fornecedor não encontrado para edição.");
                request.setAttribute("tipoMensagem", "erro");
            }

            listarFornecedores(request, response);

        } catch (Exception e) {
            request.setAttribute("mensagem", "Erro ao carregar fornecedor para edição: " + e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            listarFornecedores(request, response);
        }
    }

    private void editarFornecedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nomeOriginal = request.getParameter("nomeOriginal");
        String novoNome = request.getParameter("nomeFornecedor");
        String contato = request.getParameter("contatoFornecedor");
        String email = request.getParameter("emailFornecedor");

        String mensagem;
        String tipoMensagem;

        if (novoNome == null || novoNome.trim().isEmpty()) {
            mensagem = "Erro: Nome do fornecedor é obrigatório.";
            tipoMensagem = "erro";
        } else if (nomeOriginal == null || nomeOriginal.trim().isEmpty()) {
            mensagem = "Erro: Nome original não encontrado para edição.";
            tipoMensagem = "erro";
        } else {
            try {
                FornecedorMaterial fornecedor = new FornecedorMaterial(novoNome, contato, email);
                FornecedorMaterialService service = new FornecedorMaterialService();

                if (service.atualizarFornecedor(nomeOriginal, fornecedor)) {
                    mensagem = "Fornecedor atualizado com sucesso!";
                    tipoMensagem = "sucesso";
                } else {
                    mensagem = "Falha ao atualizar o fornecedor.";
                    tipoMensagem = "erro";
                }
            } catch (Exception e) {
                mensagem = "Erro ao atualizar fornecedor: " + e.getMessage();
                tipoMensagem = "erro";
            }
        }

        request.setAttribute("mensagem", mensagem);
        request.setAttribute("tipoMensagem", tipoMensagem);
        request.setAttribute("modoEdicao", false);
        listarFornecedores(request, response);
    }

    private void excluirFornecedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String mensagem;
        String tipoMensagem;

        if (nome == null || nome.trim().isEmpty()) {
            mensagem = "Erro: Nome do fornecedor para exclusão é obrigatório.";
            tipoMensagem = "erro";
        } else {
            try {
                FornecedorMaterialService service = new FornecedorMaterialService();

                if (service.excluirFornecedor(nome)) {
                    mensagem = "Fornecedor '" + nome + "' excluído com sucesso!";
                    tipoMensagem = "sucesso";
                } else {
                    mensagem = "Falha ao excluir o fornecedor '" + nome + "'. Fornecedor não encontrado.";
                    tipoMensagem = "erro";
                }
            } catch (Exception e) {
                mensagem = "Erro de sistema ao excluir fornecedor: " + e.getMessage();
                tipoMensagem = "erro";
            }
        }

        request.setAttribute("mensagem", mensagem);
        request.setAttribute("tipoMensagem", tipoMensagem);
        listarFornecedores(request, response);
    }

    private void pesquisarFornecedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("searchFornecedor");

        try {
            FornecedorMaterialService service = new FornecedorMaterialService();
            List<FornecedorMaterial> fornecedoresPesquisados = service.pesquisarFornecedores(nome);

            if (fornecedoresPesquisados.isEmpty()) {
                request.setAttribute("mensagem", "Nenhum fornecedor encontrado para: " + nome);
                request.setAttribute("tipoMensagem", "alerta");
            } else {
                request.setAttribute("mensagem", "Resultado da pesquisa por: " + nome);
                request.setAttribute("tipoMensagem", "sucesso");
            }

            request.setAttribute("fornecedores", fornecedoresPesquisados);
            request.setAttribute("searchFornecedor", nome);

            request.getRequestDispatcher("fornecedores_materiais.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("mensagem", "Erro ao pesquisar fornecedores: " + e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            listarFornecedores(request, response);
        }
    }

    private void listarFornecedores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            FornecedorMaterialService service = new FornecedorMaterialService();
            List<FornecedorMaterial> todosFornecedores = service.listarTodosFornecedores();

            request.setAttribute("fornecedores", todosFornecedores);

            // Manter mensagem se existir
            String mensagem = request.getParameter("mensagem");
            String tipoMensagem = request.getParameter("tipoMensagem");
            if (mensagem != null && tipoMensagem != null) {
                request.setAttribute("mensagem", mensagem);
                request.setAttribute("tipoMensagem", tipoMensagem);
            }

            request.getRequestDispatcher("fornecedores_materiais.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("mensagem", "Erro ao carregar fornecedores: " + e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("fornecedores_materiais.jsp").forward(request, response);
        }
    }
}