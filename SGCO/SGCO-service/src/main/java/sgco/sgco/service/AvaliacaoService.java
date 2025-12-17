package sgco.sgco.service;
import sgco.model.dao.AvaliacaoDAO;
import sgco.sgco.domain.Avaliacao;
import java.util.List;

public class AvaliacaoService {
    private AvaliacaoDAO avaliacaoDAO;
    public AvaliacaoService() {
        avaliacaoDAO = new AvaliacaoDAO();
    }
    public List<Avaliacao> avaliar(Avaliacao avaliacao) throws Exception {
        validar(avaliacao);
        return avaliacaoDAO.avaliar(avaliacao);
    }

    private void validar(Avaliacao avaliacao) throws Exception{
        if (avaliacao.getNota() == null) {
            throw new Exception("A nota não foi selecionada");
        }
        if (avaliacao.getProfissional() == null || avaliacao.getProfissional().isBlank()) {
            throw new Exception("O profissional não foi preenchido");
        }
    }
}
