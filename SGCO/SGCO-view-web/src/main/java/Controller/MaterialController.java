package Controller;

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
        if (action == null) {
            action = "listar";
        }

        switch (action) {
            case "cadastrar":
                cadastrarMaterial(request, response);
                break;
            case "excluir":
                excluirMaterial(request, response);
                break;
            default:
                listarMateriais(request, response);
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
                pesquisarMaterial(request, response);
                break;
            default:
                listarMateriais(request, response);
                break;
        }
    }

    private void cadastrarMaterial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nome = request.getParameter("nome");
            String quantidadeStr = request.getParameter("quantidade");
            int quantidade = Integer.parseInt(quantidadeStr);

            Material material = new Material(nome, quantidade);
            MaterialService service = new MaterialService();
            service.cadastrarMaterial(material);

            listarMateriais(request, response);
            request.setAttribute("msg", "Cadastro realizado com sucesso!");
            request.setAttribute("tipoMensagem", "sucesso");
            request.getRequestDispatcher("estoque/estoque.jsp").forward(request, response);

        } catch(Exception e){
            listarMateriais(request, response);
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("estoque/estoque.jsp").forward(request, response);
        }
    }

    private void excluirMaterial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nome = request.getParameter("nome");

            MaterialService service = new MaterialService();
            service.excluirMaterial(nome);

            listarMateriais(request, response);
            request.setAttribute("msg", "Exclusão realizada com sucesso!");
            request.setAttribute("tipoMensagem", "sucesso");
            request.getRequestDispatcher("estoque/estoque.jsp").forward(request, response);

        } catch (Exception e) {
            listarMateriais(request, response);
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("estoque/estoque.jsp").forward(request, response);
        }
    }

    private void pesquisarMaterial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nome = request.getParameter("nome");

            MaterialService service = new MaterialService();
            List<Material> resultados = service.pesquisarMateriais(nome);

            if (resultados.isEmpty()) {
                request.setAttribute("msg", "Nenhum material encontrado.");
                request.setAttribute("tipoMensagem", "alerta");
            } else {
                request.setAttribute("msg", "Pesquisa realizada com sucesso!");
                request.setAttribute("tipoMensagem", "sucesso");
            }

            request.setAttribute("materiais", resultados);
            request.setAttribute("searchMaterial", nome != null ? nome : "");

            request.getRequestDispatcher("estoque/estoque.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("estoque/estoque.jsp").forward(request, response);
        }
    }

    private void listarMateriais(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            MaterialService service = new MaterialService();
            List<Material> todos = service.listarTodosMateriais();

            request.setAttribute("materiais", todos);
            request.setAttribute("msg", "Listagem realizada com sucesso!");
            request.setAttribute("tipoMensagem", "sucesso");

            String mensagem = request.getParameter("mensagem");
            String tipoMensagem = request.getParameter("tipoMensagem");
            if (mensagem != null && tipoMensagem != null) {
                request.setAttribute("msg", mensagem);
                request.setAttribute("tipoMensagem", tipoMensagem);
            }

            request.getRequestDispatcher("estoque/estoque.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("estoque/estoque.jsp").forward(request, response);
        }
    }
}