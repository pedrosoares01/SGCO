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
            SaldoService saldoService = new SaldoService();
            List<SaldoPaciente> saldoPacientes = saldoService.gerarRelatorioDevedores();

            request.setAttribute("saldos", saldoPacientes);

            request.setAttribute("msg", "relatório gerado com sucesso");
            request.setAttribute("tipoMensagem", "sucesso");
            request.getRequestDispatcher("saldo/saldo.jsp").forward(request, response);
        }catch (Exception e){
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("saldo/saldo.jsp").forward(request, response);
        }
    }

    private void gerarRelatorioDevedores(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            SaldoService saldoService = new SaldoService();
            List<SaldoPaciente> saldoPacientes = saldoService.gerarRelatorioDevedores();

            request.setAttribute("saldos", saldoPacientes);

            request.setAttribute("msg", "relatório gerado com sucesso");
            request.setAttribute("tipoMensagem", "sucesso");
            request.getRequestDispatcher("saldo/saldo.jsp").forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void gerarRelatorioDevedoresAtrasados(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SaldoService saldoService = new SaldoService();
        SaldoDAO saldoDAO = new SaldoDAO();

        List<SaldoPaciente> saldoPacientes = saldoDAO.resgatarDividas();
        saldoService.gerarRelatorioDevedoresAtrasados(saldoPacientes);
    }



}
