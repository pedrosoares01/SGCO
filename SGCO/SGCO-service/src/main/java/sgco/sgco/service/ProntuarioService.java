package sgco.sgco.service;

import sgco.model.dao.ProntuarioDAO;
import sgco.sgco.domain.Prontuario;
import java.util.List;

public class ProntuarioService {
    private ProntuarioDAO prontuarioDAO;
    public ProntuarioService() {
        prontuarioDAO = new ProntuarioDAO();
    }
    public void cadastrar(Prontuario prontuario) throws  Exception{
        validar(prontuario);
        prontuarioDAO.cadastrar(prontuario);
    }
    public List<Prontuario> pesquisarProntuario(Prontuario prontuario) throws Exception{
        return prontuarioDAO.pesquisar(prontuario);
    }
    public void excluir(int id) throws Exception{
        prontuarioDAO.excluir(id);
    }
    public Prontuario pesquisarProntuarioId(int id) throws Exception{
        return prontuarioDAO.pesquisarId(id);
    }
    public void atualizar(Prontuario prontuario) throws Exception{
        validar(prontuario);
        prontuarioDAO.atualizar(prontuario);
    }
    private void validar(Prontuario prontuario) throws Exception{
        if(prontuario.getNome() == null || prontuario.getNome().isBlank()){
            throw new Exception("O nome não foi preenchido");
        }
        if(prontuario.getConvenio() == null || prontuario.getConvenio().isBlank()){
            throw new Exception("O convenio não foi preenchido");
        }
        if(prontuario.getEndereco() == null || prontuario.getEndereco().isBlank()){
            throw new Exception("O endereco não foi preenchido");
        }
        if(prontuario.getUltimo_exame() == null || prontuario.getUltimo_exame().isBlank()){
            throw new Exception("A data do ultimo exame não foi preenchida");
        }
        if(prontuario.getProfissional() == null || prontuario.getProfissional().isBlank()){
            throw new Exception("O profissional não foi preenchido");
        }
        if(prontuario.getTelefone() == null || prontuario.getTelefone().isBlank()){
            throw new Exception("O telefone não foi preenchido");
        }
        if(prontuario.getNaturalidade() == null || prontuario.getNaturalidade().isBlank()){
            throw new Exception("A naturalidade não foi preenchida");
        }
        if(prontuario.getProfissao() == null || prontuario.getProfissao().isBlank()){
            throw new Exception("A profissao não foi preenchida");
        }
        if(prontuario.getNascimento() == null || prontuario.getNascimento().isBlank()){
            throw new Exception("A data de nascimento não foi preenchida");
        }
        if(prontuario.getEstado_civil() == null || prontuario.getEstado_civil().isBlank()){
            throw new Exception("O estado civil não foi preenchido");
        }
        if(prontuario.getIndicacao() == null || prontuario.getIndicacao().isBlank()){
            throw new Exception("A indicacao não foi preenchida");
        }
        if(prontuario.getSexo() == null || prontuario.getSexo().toString().isBlank()){
            throw new Exception("O sexo não foi preenchido");
        }
        if(prontuario.getQp() == null || prontuario.getQp().isBlank()){
            throw new Exception("A QP não foi preenchida");
        }
        if(prontuario.getHda() == null || prontuario.getHda().isBlank()){
            throw new Exception("A HDA não foi preenchida");
        }
    }
}
