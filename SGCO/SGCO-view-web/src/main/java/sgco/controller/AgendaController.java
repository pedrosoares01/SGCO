package Controller;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sgco.sgco.domain.Agenda;
import sgco.sgco.domain.Usuario;
import sgco.sgco.service.AgendaService;

@WebServlet(name = "AgendaController", urlPatterns = {"/AgendaController"})
public class AgendaController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        request.getAttribute("profissionais");
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
                break;
            case "carregar":
                listarProfissionais(request, response);
                break;
            case "horarios":
                carregarHorarios(request, response);
                break;
            default:
                break;
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "carregar";

        switch (action) {
            case "horarios":
                carregarHorarios(request, response);
                break;
            case "carregar":
            default:
                listarProfissionais(request, response);
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
            listarProfissionais(request, response);
            carregarProfissionais(request);
            request.setAttribute("msg", "Agendamento realizado com sucesso!");
            request.setAttribute("tipoMensagem", "sucesso");
            request.getRequestDispatcher("agenda/agenda.jsp").forward(request, response);
        } catch(Exception e){
            carregarProfissionais(request);
            listarProfissionais(request, response);
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("agenda/agenda.jsp").forward(request, response);
        }
    }
    protected void pesquisar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nome = request.getParameter("nome");
            List<Agenda> lista;
            Agenda agenda = new Agenda();
            agenda.setProfissional(nome);
            AgendaService agendaService = new AgendaService();
            lista = agendaService.pesquisarAgendamento(agenda);
            carregarProfissionais(request);
            request.setAttribute("nome", nome);
            request.setAttribute("resultados", lista);
            request.getRequestDispatcher("agenda/agenda.jsp").forward(request, response);
        } catch (Exception e){
            carregarProfissionais(request);
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("agenda/agenda.jsp").forward(request, response);
        }
    }
    protected void listarProfissionais(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            carregarProfissionais(request);
            request.getRequestDispatcher("agenda/agenda.jsp").forward(request, response);
        } catch (Exception e){
            request.getRequestDispatcher("agenda/agenda.jsp").forward(request, response);
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

    private void carregarHorarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String idProf = request.getParameter("profissional");
            String data = request.getParameter("data");
            AgendaService agendaService = new AgendaService();
            List<Agenda> ocupados = agendaService.listarHorariosOcupados(idProf, data);
            request.setAttribute("ocupados", ocupados);
            request.setAttribute("data", data);
            request.getRequestDispatcher("agenda/agenda.jsp").forward(request, response);
        } catch (Exception e) {
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
            request.setAttribute("nomeListar", nome);
            request.setAttribute("resultadosListar", lista);
            request.getRequestDispatcher("agenda/agenda.jsp").forward(request, response);
        } catch (Exception e){
            request.getRequestDispatcher("agenda/agenda.jsp").forward(request, response);
        }
    }
}