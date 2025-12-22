package sgco.controller;

import sgco.model.dao.EquipamentosDAO;
import sgco.model.domain.Equipamentos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/EquipamentoServlet")
public class EquipamentoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codigo = request.getParameter("codigo");
        Equipamentos eq = null;

        try {
            if (codigo != null && !codigo.trim().isEmpty()) {
                EquipamentosDAO dao = new EquipamentosDAO();
                eq = dao.buscarPorCodigo(codigo);
            }

            request.setAttribute("equipamento", eq);

            request.getRequestDispatcher("/core/controleequipamentos/controleequip.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Erro ao buscar equipamento", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}