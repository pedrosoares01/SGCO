package sgco.sgco.service;

import sgco.model.dao.FornecedorServicoDAO;
import sgco.sgco.domain.FornecedorServico;
import java.sql.SQLException;
import java.util.List;

public class FornecedorServicoService {
    private FornecedorServicoDAO fornecedorServicoDAO;

    public FornecedorServicoService() {
        this.fornecedorServicoDAO = new FornecedorServicoDAO();
    }

    public boolean cadastrarFornecedorServico(FornecedorServico fornecedorServico) throws SQLException {
        return fornecedorServicoDAO.inserir(fornecedorServico);
    }

    public FornecedorServico buscarFornecedorServico(String nomeServico, String fornecedor) throws SQLException {
        return fornecedorServicoDAO.buscarPorChave(nomeServico, fornecedor);
    }

    public boolean atualizarFornecedorServico(String nomeServicoOriginal, String fornecedorOriginal,
                                              FornecedorServico fornecedorServico) throws SQLException {
        return fornecedorServicoDAO.atualizar(nomeServicoOriginal, fornecedorOriginal, fornecedorServico);
    }

    public boolean excluirFornecedorServico(String nomeServico, String fornecedor) throws SQLException {
        return fornecedorServicoDAO.excluir(nomeServico, fornecedor);
    }

    public List<FornecedorServico> pesquisarFornecedoresServicos(String nomeServico, String fornecedor) throws SQLException {
        return fornecedorServicoDAO.pesquisar(nomeServico, fornecedor);
    }

    public List<FornecedorServico> listarTodosFornecedoresServicos() throws SQLException {
        return fornecedorServicoDAO.listarTodos();
    }
}