package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sgco.sgco.domain.Agenda;
import sgco.sgco.domain.Usuario;
import sgco.sgco.service.AgendaService;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "PacientesAgendadosController", urlPatterns = {"/PacientesAgendadosController"})
public class PacientesAgendadosController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.equals("gerar-relatorio") ) {
            gerarRelatorio(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listarProfissionais(request, response);
    }
    private void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nome = request.getParameter("profissional");
            List<Agenda> lista;
            Agenda agenda = new Agenda();
            agenda.setPaciente(nome);
            agenda.setProfissional(nome);
            AgendaService agendaService = new AgendaService();
            lista = agendaService.pesquisar(agenda);
            request.setAttribute("nome", nome);
            request.setAttribute("resultados", lista);
            carregarProfissionais(request);
            request.getRequestDispatcher("pacientes-agendados/pacientes_agendados.jsp").forward(request, response);
        } catch (Exception e){
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("pacientes-agendados/pacientes_agendados.jsp").forward(request, response);
        }
    }
    protected void listarProfissionais(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            carregarProfissionais(request);
            request.getRequestDispatcher("pacientes-agendados/pacientes_agendados.jsp").forward(request, response);
        } catch (Exception e){
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("pacientes-agendados/pacientes_agendados.jsp").forward(request, response);
        }
    }
    protected void carregarProfissionais(HttpServletRequest request) {
        try {
            AgendaService service = new AgendaService();
            List<Usuario> lista = service.listarProfissionais();
            request.setAttribute("profissionais", lista);
        } catch (Exception e) {
            request.setAttribute("profissionais", null);
        }
    }
}
