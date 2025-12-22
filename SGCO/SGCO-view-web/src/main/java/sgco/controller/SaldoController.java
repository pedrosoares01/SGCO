package sgco.controller;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sgco.model.dao.SaldoDAO;
import sgco.sgco.domain.SaldoPaciente;
import sgco.sgco.service.SaldoService;

@WebServlet(name = "SaldoController", urlPatterns = {"/SaldoController"})
public class SaldoController extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            String acao = request.getParameter("acao");
            switch (acao) {
                case "atualizar":
                    atualizarDevedores(request, response);
                    break;
                case "relatorio":
                    gerarRelatorioDevedores(request,response);
                    break;
                case "pesquisar":
                    pesquisarDevedores(request,response);
                    break;
                case "redirecionar":
                    redirecionarPaginaEditar(request,response);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void gerarRelatorioDevedores(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            SaldoService saldoService = new SaldoService();
            List<SaldoPaciente> saldoPacientes = saldoService.gerarRelatorioDevedores();

            request.setAttribute("saldos", saldoPacientes);

            Boolean pesquisaAtiva = Boolean.valueOf(request.getParameter("pesquisaAtiva"));

            if (!Boolean.TRUE.equals(pesquisaAtiva)) {
                request.setAttribute("msg", "relatório gerado com sucesso");
                request.setAttribute("tipoMensagem", "sucesso");
            }

            request.getRequestDispatcher("saldo/saldo.jsp").forward(request, response);
        }catch (Exception e){
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("saldo/saldo.jsp").forward(request, response);
        }
    }


    private void redirecionarPaginaEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SaldoPaciente saldoPaciente = new SaldoPaciente();
        saldoPaciente.setNomeDevedor(request.getParameter("nomeDevedor"));
        saldoPaciente.setPago(Double.parseDouble(request.getParameter("valorPago")));
        saldoPaciente.setDevido(Double.parseDouble(request.getParameter("valorDevido")));
        saldoPaciente.setTotalPagar(Double.parseDouble(request.getParameter("totalPagar")));
        saldoPaciente.setPacienteId(Integer.parseInt(request.getParameter("pacienteId")));
        saldoPaciente.setOrcamentoId(Integer.parseInt(request.getParameter("orcamentoId")));
        request.setAttribute("saldoPaciente", saldoPaciente);
        request.getRequestDispatcher("saldo/editarSaldo.jsp").forward(request, response);
    }

    private void atualizarDevedores(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            SaldoService saldoService = new SaldoService();
            SaldoPaciente saldoPaciente = new SaldoPaciente();

            saldoPaciente.setNomeDevedor(request.getParameter("nomeDevedor"));
            saldoPaciente.setPago(Double.parseDouble(request.getParameter("valorPago")));
            saldoPaciente.setTotalPagar(Double.parseDouble(request.getParameter("totalPagar")));
            saldoPaciente.setPacienteId(Integer.parseInt(request.getParameter("pacienteId")));
            saldoPaciente.setOrcamentoId(Integer.parseInt(request.getParameter("orcamentoId")));
            saldoService.atualizarDevedores(saldoPaciente);

            request.setAttribute("msg", "atualização realizada com sucesso");
            request.setAttribute("tipoMensagem", "sucesso");
            request.getRequestDispatcher("saldo/saldo.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("saldo/saldo.jsp").forward(request, response);
        }
    }

    private void pesquisarDevedores(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            SaldoService saldoService = new SaldoService();
            SaldoPaciente saldoPaciente = new SaldoPaciente();

            saldoPaciente.setNomeDevedor(request.getParameter("pesquisarNome"));
            List<SaldoPaciente> saldos = saldoService.pesquisarDevedores(saldoPaciente);
            request.setAttribute("saldos", saldos);
            request.setAttribute("pesquisaAtiva", true);
            request.getRequestDispatcher("saldo/saldo.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("saldo/saldo.jsp").forward(request, response);
        }
    }

}
