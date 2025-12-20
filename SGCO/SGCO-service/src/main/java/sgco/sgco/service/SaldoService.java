package sgco.sgco.service;

import java.util.List;

import sgco.model.dao.SaldoDAO;
import sgco.sgco.domain.SaldoPaciente;

public class SaldoService {

    private SaldoDAO saldoDAO;

    public SaldoService() {
       saldoDAO =  new SaldoDAO();
    }

    public List<SaldoPaciente> gerarRelatorioDevedores() throws Exception {
        return saldoDAO.resgatarDividas();
    }

    public void gerarRelatorioDevedoresAtrasados(List<SaldoPaciente> pacientes) {

    }

}
