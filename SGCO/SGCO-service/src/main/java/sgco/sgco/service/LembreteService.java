package sgco.sgco.service;

import sgco.model.domain.Paciente;
import sgco.sgco.dao.ram.LembreteDAO;

public class LembreteService {

    private LembreteDAO lembreteDAO;
    private EmailService emailService;

    public LembreteService(){
        lembreteDAO = new LembreteDAO();
        emailService = new EmailService();
    }

    public void enviarLembrete(String nomePaciente) throws Exception {
        Paciente paciente = new Paciente();
        paciente.setNome(nomePaciente);

        paciente = lembreteDAO.LembrarPaciente(paciente);

        emailService.enviarEmail(
                paciente.getEmail(),
                "Lembrete de consulta",
                "Olá " + paciente.getNome() +
                        ", este é um lembrete de sua consulta marcada para amanhã."
        );

    }
}
