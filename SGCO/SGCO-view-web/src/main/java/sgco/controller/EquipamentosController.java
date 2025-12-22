package sgco.controller;

import sgco.model.dao.EquipamentosDAO;
import sgco.model.domain.Equipamentos;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EquipamentosController", urlPatterns = {"/EquipamentosController"})
public class EquipamentosController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if (acao == null) {
            acao = "pesquisar";
        }

        switch (acao) {
            case "editar":
                editarEquipamento(request, response);
                break;
            case "atualizar":
                atualizarEquipamento(request, response);
                break;
            case "excluir":
                excluirEquipamento(request, response);
                break;
            case "pesquisar":
            default:
                pesquisarEquipamento(request, response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if ("excluir".equals(acao)) {
            excluirEquipamento(request, response);
        } else if ("editar".equals(acao)) {
            editarEquipamento(request, response);
        } else {
            response.sendRedirect("core/controleequipamentos/controleequip.jsp");
        }
    }

    // ---------------- MÉTODO EDITAR -----------------
    private void editarEquipamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            enviarMensagem(request, response, "ID não informado.", "erro");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            EquipamentosDAO dao = new EquipamentosDAO();
            Equipamentos eq = dao.buscarPorId(id);

            if (eq != null) {
                request.setAttribute("equipamento", eq);
                request.getRequestDispatcher("/core/controleequipamentos/editar.jsp").forward(request, response);
            }


        } catch (Exception ex) {
            enviarMensagem(request, response, "Erro ao carregar equipamento: " + ex.getMessage(), "erro");
        }
    }

    // ---------------- MÉTODO ATUALIZAR -----------------
    private void atualizarEquipamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        String nome = request.getParameter("nome");
        String codigo = request.getParameter("codigo");
        String local = request.getParameter("local");
        String ultima = request.getParameter("ultima");
        String freqStr = request.getParameter("freq");
        String status = request.getParameter("status");

        if (idStr == null || idStr.isEmpty()) {
            enviarMensagem(request, response, "ID inválido.", "erro");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            int freq = Integer.parseInt(freqStr);

            Equipamentos e = new Equipamentos(id, nome, codigo, local, ultima, freq, status);

            EquipamentosDAO dao = new EquipamentosDAO();
            boolean sucesso = dao.atualizar(e);

            if (sucesso) {
                enviarMensagem(request, response, "Equipamento atualizado com sucesso!", "sucesso");
            } else {
                enviarMensagem(request, response, "Erro ao atualizar equipamento.", "erro");
            }

        } catch (Exception ex) {
            enviarMensagem(request, response, "Erro inesperado: " + ex.getMessage(), "erro");
        }
    }

    // ---------------- MÉTODO EXCLUIR -----------------
    private void excluirEquipamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            enviarMensagem(request, response, "ID não informado.", "erro");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            EquipamentosDAO dao = new EquipamentosDAO();
            boolean sucesso = dao.excluir(id);

            if (sucesso) {
                enviarMensagem(request, response, "Equipamento excluído com sucesso!", "sucesso");
            } else {
                enviarMensagem(request, response, "Erro ao excluir equipamento.", "erro");
            }

        } catch (Exception ex) {
            enviarMensagem(request, response, "Erro ao excluir: " + ex.getMessage(), "erro");
        }
    }

    // ---------------- MÉTODO PESQUISAR (LISTAR) -----------------
    private void pesquisarEquipamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("search");

        try {
            EquipamentosDAO dao = new EquipamentosDAO();
            List<Equipamentos> resultados = dao.pesquisarPorNome(nome);

            request.setAttribute("listaEquipamentos", resultados);
            request.getRequestDispatcher("/core/controleequipamentos/controleequip.jsp").forward(request, response);

        } catch (Exception ex) {
            enviarMensagem(request, response, "Erro ao pesquisar equipamentos: " + ex.getMessage(), "erro");
        }
    }

    // ---------------- ENVIO DE MENSAGEM -----------------
    private void enviarMensagem(HttpServletRequest request, HttpServletResponse response,
                                String mensagem, String tipo)
            throws ServletException, IOException {

        request.setAttribute("mensagem", mensagem);
        request.setAttribute("tipoMensagem", tipo);

        request.getRequestDispatcher("/core/controleequipamentos/controleequip.jsp").forward(request, response);
    }
}
