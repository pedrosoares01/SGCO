package sgco.sgco.service;
import sgco.sgco.dao.ram.AgendaDAO;
import sgco.sgco.domain.Agenda;
import java.sql.ResultSet;
import java.util.List;

public class AgendaService {
    private AgendaDAO agendaDAO;
    public AgendaService() {
        agendaDAO = new AgendaDAO();
    }
    public void agendar(Agenda agenda) throws Exception {
        validar(agenda);
        agendaDAO.agendar(agenda);
    }

    public List<Agenda> pesquisar(Agenda agenda) throws Exception {
        return agendaDAO.pesquisar(agenda);
    }

    private void validar(Agenda agenda) throws Exception{
        if (agenda.getData() == null || agenda.getData().isBlank()) {
            throw new Exception("A data não foi preenchida");
        }
        if (agenda.getPaciente() == null || agenda.getPaciente().isBlank()) {
            throw new Exception("O paciente não foi preenchida");
        }
        if (agenda.getProfissional() == null || agenda.getProfissional().isBlank()) {
            throw new Exception("O profissional não foi preenchida");
        }
        if (agenda.getHora() == null || agenda.getHora().isBlank()) {
            throw new Exception("A hora não foi preenchida");
        }
    }
}