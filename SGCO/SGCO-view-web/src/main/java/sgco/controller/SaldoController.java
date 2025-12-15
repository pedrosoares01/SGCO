package sgco.controller;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sgco.model.dao.SaldoDAO;
import sgco.sgco.service.SaldoService;

@WebServlet(name = "SaldoController", urlPatterns = {"/SaldoController"})
public class SaldoController extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        String action = request.getParameter("action");

        switch (action){

            case "Devedores":
                gerarRelatorioDevedores(request, response);
                break;
            case "DevedoresAtrasados":
                gerarRelatorioDevedoresAtrasados(request, response);
                break;
            default:
                break;
        }

    }

    private void gerarRelatorioDevedores(HttpServletRequest request, HttpServletResponse response){
        SaldoService saldoService = new SaldoService();
        saldoService.gerarRelatorioDevedores();
    }


    private void gerarRelatorioDevedoresAtrasados(HttpServletRequest request, HttpServletResponse response){
        SaldoService saldoService = new SaldoService();
        saldoService.gerarRelatorioDevedoresAtrasados();
    }



}
