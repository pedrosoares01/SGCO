package Controller;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sgco.sgco.domain.Agenda;
import sgco.sgco.service.AgendaService;

@WebServlet(name = "AgendaController", urlPatterns = {"/AgendaController"})
public class AgendaController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "pesquisar";
        }
        switch (action) {
            case "agendar":
                agendar(request, response);
                break;
            case "pesquisar":
                pesquisar(request, response);
                break;
            case "listar":
                listar(request, response);
            default:
                break;
        }
    }

    private void agendar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String paciente, profissional, data, hora;
            paciente = request.getParameter("paciente");
            profissional = request.getParameter("profissional");
            data = request.getParameter("data");
            hora = request.getParameter("hora");
            Agenda agenda = new Agenda();
            agenda.setPaciente(paciente);
            agenda.setProfissional(profissional);
            agenda.setData(data);
            agenda.setHora(hora);
            AgendaService agendaService = new AgendaService();
            agendaService.agendar(agenda);
            request.getRequestDispatcher("agenda/agenda.jsp").forward(request, response);
        } catch(Exception e){
            request.getRequestDispatcher("agenda/agenda.jsp").forward(request, response);
        }
    }
    protected void pesquisar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nome = request.getParameter("nome");
            List<Agenda> lista;
            Agenda agenda = new Agenda();
            agenda.setPaciente(nome);
            agenda.setProfissional(nome);
            AgendaService agendaService = new AgendaService();
            lista = agendaService.pesquisar(agenda);
            request.setAttribute("nome", nome);
            request.setAttribute("resultados", lista);
            request.getRequestDispatcher("agenda/agenda.jsp").forward(request, response);
        } catch (Exception e){
            request.getRequestDispatcher("agenda/agenda.jsp").forward(request, response);
        }
    }

    protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nome = request.getParameter("nome");
            List<Agenda> lista;
            Agenda agenda = new Agenda();
            agenda.setProfissional(nome);
            AgendaService agendaService = new AgendaService();
            lista = agendaService.listar(agenda);
            request.setAttribute("nome", nome);
            request.setAttribute("resultados", lista);
            request.getRequestDispatcher("agenda/agenda.jsp").forward(request, response);
        } catch (Exception e){
            request.getRequestDispatcher("agenda/agenda.jsp").forward(request, response);
        }
    }
}