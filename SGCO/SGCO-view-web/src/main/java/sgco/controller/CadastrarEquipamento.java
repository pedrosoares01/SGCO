package sgco.controller;

import sgco.model.dao.EquipamentosDAO;
import sgco.model.domain.Equipamentos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CadastrarEquipamento", urlPatterns = {"/CadastrarEquipamento"})
public class CadastrarEquipamento extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");

            String nome = request.getParameter("nome");
            String codigo = request.getParameter("codigo");
            String local = request.getParameter("local");
            String ultima = request.getParameter("ultima");
            String status = request.getParameter("status");

            // evita erro caso freq venha vazio
            String freqStr = request.getParameter("freq");
            int freq = (freqStr == null || freqStr.isEmpty()) ? 0 : Integer.parseInt(freqStr);

            // criar objeto do modelo correto
            Equipamentos eq = new Equipamentos(nome, codigo, local, ultima, freq, status);

            EquipamentosDAO dao = new EquipamentosDAO();
            boolean sucesso = dao.inserir(eq);

            if (sucesso) {
                request.getSession().setAttribute("popup", "Equipamento cadastrado com sucesso!");
            } else {
                request.getSession().setAttribute("popup", "Erro ao cadastrar equipamento.");
            }

        } catch (Exception ex) {
            request.getSession().setAttribute("popup", "Erro inesperado: " + ex.getMessage());
        }

        // volta para a página principal
        response.sendRedirect("core/controleequipamentos/controleequip.jsp");
    }
}
