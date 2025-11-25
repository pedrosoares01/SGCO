    package Controller;
    import java.io.IOException;

    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import sgco.sgco.service.LembreteService;

    @WebServlet(name = "LembreteController", urlPatterns = {"/LembreteController"})
    public class LembreteController extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String[] nomesPacientes = request.getParameterValues("pacientes");

            if (nomesPacientes == null || nomesPacientes.length == 0) {
                response.sendRedirect("agenda.jsp?msg=Nenhum paciente selecionado");
                return;
            }

            LembreteService lembreteService = new LembreteService();

            for (String nome : nomesPacientes) {
                try {
                    lembreteService.enviarLembrete(nome);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            response.sendRedirect("agenda.jsp?msg=Lembretes enviados com sucesso!");
        }
    }

