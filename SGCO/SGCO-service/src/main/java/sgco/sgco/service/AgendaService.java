package sgco.sgco.service;
import sgco.model.dao.AgendaDAO;
import sgco.sgco.domain.Agenda;
import sgco.sgco.domain.Usuario;
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
    public List<Usuario> listarProfissionais() throws Exception {
        return agendaDAO.listarProfissionais();
    }
    public List<Agenda> listarHorariosOcupados(String profissional, String data) throws Exception {
        return agendaDAO.listarHorariosOcupados(profissional, data);
    }
    public List<Agenda> pesquisarAgendamento(Agenda profissional) throws Exception {
        return agendaDAO.pesquisarAgendamento(profissional);
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