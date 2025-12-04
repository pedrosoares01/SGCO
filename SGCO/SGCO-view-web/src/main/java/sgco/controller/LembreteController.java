    package sgco.controller;
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
            try {
                String[] nomesPacientes = request.getParameterValues("pacientes");

                if (nomesPacientes == null || nomesPacientes.length == 0) {
                   throw new Exception("Erro ao Listar pacientes.");
                }

                LembreteService lembreteService = new LembreteService();

                for (String nome : nomesPacientes) {
                    lembreteService.enviarLembrete(nome);

                }
                request.setAttribute("mensagem", "Lembretes enviados com sucesso!");
                request.setAttribute("tipoMensagem", "sucesso");
                request.getRequestDispatcher("agenda/agenda.jsp").forward(request, response);
            }catch(Exception e) {
                request.setAttribute("mensagem", e.getMessage());
                request.setAttribute("tipoMensagem", "erro");
                request.getRequestDispatcher("agenda/agenda.jsp").forward(request, response);
            }
        }
    }

