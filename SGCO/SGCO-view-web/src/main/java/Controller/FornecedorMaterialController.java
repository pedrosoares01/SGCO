package Controller;

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
        if (action == null) {
            action = "listar";
        }

        switch (action) {
            case "cadastrar":
                cadastrarFornecedorMaterial(request, response);
                break;
            case "editar":
                editarFornecedorMaterial(request, response);
                break;
            case "excluir":
                excluirFornecedorMaterial(request, response);
                break;
            default:
                listarFornecedoresMateriais(request, response);
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
                pesquisarFornecedorMaterial(request, response);
                break;
            case "carregarEdicao":
                carregarFornecedorMaterialParaEdicao(request, response);
                break;
            default:
                listarFornecedoresMateriais(request, response);
                break;
        }
    }

    private void cadastrarFornecedorMaterial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nomeFornecedor = request.getParameter("nomeFornecedor");
            String contatoFornecedor = request.getParameter("contatoFornecedor");
            String emailFornecedor = request.getParameter("emailFornecedor");

            FornecedorMaterial fornecedorMaterial = new FornecedorMaterial(nomeFornecedor, contatoFornecedor, emailFornecedor);
            FornecedorMaterialService service = new FornecedorMaterialService();
            service.cadastrarFornecedor(fornecedorMaterial);

            listarFornecedoresMateriais(request, response);
            request.setAttribute("msg", "Cadastro realizado com sucesso!");
            request.setAttribute("tipoMensagem", "sucesso");
            request.getRequestDispatcher("fornecedores_materiais/fornecedores_materiais.jsp").forward(request, response);

        } catch(Exception e){
            listarFornecedoresMateriais(request, response);
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("fornecedores_materiais/fornecedores_materiais.jsp").forward(request, response);
        }
    }

    private void carregarFornecedorMaterialParaEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nome = request.getParameter("nome");

            FornecedorMaterialService service = new FornecedorMaterialService();
            FornecedorMaterial fornecedorMaterial = service.buscarFornecedor(nome);

            if (fornecedorMaterial != null) {
                request.setAttribute("fornecedorMaterialEdicao", fornecedorMaterial);
                request.setAttribute("modoEdicao", true);
                request.setAttribute("nomeOriginal", nome);
                request.setAttribute("msg", "Fornecedor carregado para edição com sucesso!");
                request.setAttribute("tipoMensagem", "sucesso");
            } else {
                request.setAttribute("msg", "Fornecedor não encontrado para edição.");
                request.setAttribute("tipoMensagem", "erro");
            }

            listarFornecedoresMateriais(request, response);
            request.getRequestDispatcher("fornecedores_materiais/fornecedores_materiais.jsp").forward(request, response);

        } catch (Exception e) {
            listarFornecedoresMateriais(request, response);
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("fornecedores_materiais/fornecedores_materiais.jsp").forward(request, response);
        }
    }

    private void editarFornecedorMaterial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nomeOriginal = request.getParameter("nomeOriginal");
            String novoNome = request.getParameter("nomeFornecedor");
            String contato = request.getParameter("contatoFornecedor");
            String email = request.getParameter("emailFornecedor");

            FornecedorMaterial fornecedorMaterial = new FornecedorMaterial(novoNome, contato, email);
            FornecedorMaterialService service = new FornecedorMaterialService();
            service.atualizarFornecedor(nomeOriginal, fornecedorMaterial);

            request.setAttribute("modoEdicao", false);
            listarFornecedoresMateriais(request, response);
            request.setAttribute("msg", "Edição realizada com sucesso!");
            request.setAttribute("tipoMensagem", "sucesso");
            request.getRequestDispatcher("fornecedores_materiais/fornecedores_materiais.jsp").forward(request, response);

        } catch (Exception e) {
            listarFornecedoresMateriais(request, response);
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("fornecedores_materiais/fornecedores_materiais.jsp").forward(request, response);
        }
    }

    private void excluirFornecedorMaterial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nome = request.getParameter("nome");

            FornecedorMaterialService service = new FornecedorMaterialService();
            service.excluirFornecedor(nome);

            listarFornecedoresMateriais(request, response);
            request.setAttribute("msg", "Exclusão realizada com sucesso!");
            request.setAttribute("tipoMensagem", "sucesso");
            request.getRequestDispatcher("fornecedores_materiais/fornecedores_materiais.jsp").forward(request, response);

        } catch (Exception e) {
            listarFornecedoresMateriais(request, response);
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("fornecedores_materiais/fornecedores_materiais.jsp").forward(request, response);
        }
    }

    private void pesquisarFornecedorMaterial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nome = request.getParameter("searchFornecedor");

            FornecedorMaterialService service = new FornecedorMaterialService();
            List<FornecedorMaterial> resultados = service.pesquisarFornecedores(nome);

            if (resultados.isEmpty()) {
                request.setAttribute("msg", "Nenhum fornecedor encontrado.");
                request.setAttribute("tipoMensagem", "alerta");
            } else {
                request.setAttribute("msg", "Pesquisa realizada com sucesso!");
                request.setAttribute("tipoMensagem", "sucesso");
            }

            request.setAttribute("fornecedores", resultados);
            request.setAttribute("searchFornecedor", nome != null ? nome : "");

            request.getRequestDispatcher("fornecedores_materiais/fornecedores_materiais.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("fornecedores_materiais/fornecedores_materiais.jsp").forward(request, response);
        }
    }

    private void listarFornecedoresMateriais(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            FornecedorMaterialService service = new FornecedorMaterialService();
            List<FornecedorMaterial> todos = service.listarTodosFornecedores();

            request.setAttribute("fornecedores", todos);
            request.setAttribute("msg", "Listagem realizada com sucesso!");
            request.setAttribute("tipoMensagem", "sucesso");

            String mensagem = request.getParameter("mensagem");
            String tipoMensagem = request.getParameter("tipoMensagem");
            if (mensagem != null && tipoMensagem != null) {
                request.setAttribute("msg", mensagem);
                request.setAttribute("tipoMensagem", tipoMensagem);
            }

            request.getRequestDispatcher("fornecedores_materiais/fornecedores_materiais.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("fornecedores_materiais/fornecedores_materiais.jsp").forward(request, response);
        }
    }
}