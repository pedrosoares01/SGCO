package sgco.controller;



import sgco.sgco.service.MaterialService;
import sgco.sgco.domain.Material;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "MaterialController", urlPatterns = {"/MaterialController"})
public class MaterialController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("cadastrar".equals(action)) {
            cadastrarMaterial(request, response);
        } else if ("excluir".equals(action)) {
            excluirMaterial(request, response);
        } else {
            listarMateriais(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("pesquisar".equals(action)) {
            pesquisarMaterial(request, response);
        } else {
            listarMateriais(request, response);
        }
    }

    private void cadastrarMaterial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String quantidadeStr = request.getParameter("quantidade");

        String mensagem;
        String tipoMensagem;

        if (nome == null || nome.trim().isEmpty()) {
            mensagem = "Erro: Nome do material é obrigatório.";
            tipoMensagem = "erro";
        } else if (quantidadeStr == null) {
            mensagem = "Erro: Quantidade é obrigatória.";
            tipoMensagem = "erro";
        } else {
            try {
                int quantidade = Integer.parseInt(quantidadeStr);

                if (quantidade < 1) {
                    mensagem = "Erro: Quantidade deve ser maior que zero.";
                    tipoMensagem = "erro";
                } else {
                    Material material = new Material(nome, quantidade);
                    MaterialService service = new MaterialService();

                    if (service.cadastrarMaterial(material)) {
                        mensagem = "Material cadastrado com sucesso!";
                        tipoMensagem = "sucesso";
                    } else {
                        mensagem = "Falha ao cadastrar o material.";
                        tipoMensagem = "erro";
                    }
                }
            } catch (NumberFormatException e) {
                mensagem = "Erro: Quantidade inválida. Digite apenas números inteiros.";
                tipoMensagem = "erro";
            } catch (Exception e) {
                mensagem = "Erro de sistema ao cadastrar material: " + e.getMessage();
                tipoMensagem = "erro";
            }
        }

        request.setAttribute("mensagem", mensagem);
        request.setAttribute("tipoMensagem", tipoMensagem);
        listarMateriais(request, response);
    }

    private void excluirMaterial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String mensagem;
        String tipoMensagem;

        if (nome == null || nome.trim().isEmpty()) {
            mensagem = "Erro: Nome do material para exclusão é obrigatório.";
            tipoMensagem = "erro";
        } else {
            try {
                MaterialService service = new MaterialService();

                if (service.excluirMaterial(nome)) {
                    mensagem = "Material '" + nome + "' excluído com sucesso!";
                    tipoMensagem = "sucesso";
                } else {
                    mensagem = "Falha ao excluir o material '" + nome + "'. Material não encontrado.";
                    tipoMensagem = "erro";
                }
            } catch (Exception e) {
                mensagem = "Erro de sistema ao excluir material: " + e.getMessage();
                tipoMensagem = "erro";
            }
        }

        request.setAttribute("mensagem", mensagem);
        request.setAttribute("tipoMensagem", tipoMensagem);
        listarMateriais(request, response);
    }

    private void pesquisarMaterial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");

        try {
            MaterialService service = new MaterialService();
            List<Material> materiaisPesquisados = service.pesquisarMateriais(nome);

            if (materiaisPesquisados.isEmpty()) {
                request.setAttribute("mensagem", "Nenhum material encontrado para: " + nome);
                request.setAttribute("tipoMensagem", "alerta");
            } else {
                request.setAttribute("mensagem", "Resultado da pesquisa por: " + nome);
                request.setAttribute("tipoMensagem", "sucesso");
            }

            request.setAttribute("materiais", materiaisPesquisados);
            request.setAttribute("searchMaterial", nome);

            request.getRequestDispatcher("/estoque.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("mensagem", "Erro ao pesquisar materiais: " + e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            listarMateriais(request, response);
        }
    }

    private void listarMateriais(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            MaterialService service = new MaterialService();
            List<Material> todosMateriais = service.listarTodosMateriais();

            request.setAttribute("materiais", todosMateriais);

            String mensagem = (String) request.getAttribute("mensagem");
            String tipoMensagem = (String) request.getAttribute("tipoMensagem");
            if (mensagem == null) {
                mensagem = request.getParameter("mensagem");
                tipoMensagem = request.getParameter("tipoMensagem");
            }

            if (mensagem != null && tipoMensagem != null) {
                request.setAttribute("mensagem", mensagem);
                request.setAttribute("tipoMensagem", tipoMensagem);
            }

            request.getRequestDispatcher("/estoque.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("mensagem", "Erro ao carregar materiais: " + e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("estoque/estoque.jsp").forward(request, response);
        }
    }
}