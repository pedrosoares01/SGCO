package sgco;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sgco.sgco.domain.Avaliacao;
import sgco.sgco.service.AvaliacaoService;

@WebServlet(name = "AvaliacaoController", urlPatterns = {"/AvaliacaoController"})
public class AvaliacaoController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            avaliacao(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void avaliacao(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String profissional = request.getParameter("profissional");
            Integer nota = Integer.parseInt(request.getParameter("nota"));
            List<Avaliacao> avaliacoes;
            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setNota(nota);
            avaliacao.setProfissional(profissional);
            AvaliacaoService avaliacaoService = new AvaliacaoService();
            avaliacao.setNota(nota);
            avaliacao.setProfissional(profissional);
            avaliacoes = avaliacaoService.avaliar(avaliacao);
            request.setAttribute("avaliacoes", avaliacoes);
            request.getRequestDispatcher("avaliacao/avaliacao.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("avaliacao/avaliacao.jsp").forward(request, response);
        }
    }
}
