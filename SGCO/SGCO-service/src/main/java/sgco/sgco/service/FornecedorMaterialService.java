package sgco.sgco.service;

import sgco.model.dao.FornecedorMaterialDAO;
import sgco.sgco.domain.FornecedorMaterial;
import java.sql.SQLException;
import java.util.List;

public class FornecedorMaterialService {
    private FornecedorMaterialDAO fornecedorMaterialDAO;

    public FornecedorMaterialService() {
        this.fornecedorMaterialDAO = new FornecedorMaterialDAO();
    }

    public boolean cadastrarFornecedor(FornecedorMaterial fornecedor) throws SQLException {
        return fornecedorMaterialDAO.inserir(fornecedor);
    }

    public FornecedorMaterial buscarFornecedor(String nome) throws SQLException {
        return fornecedorMaterialDAO.buscarPorNome(nome);
    }

    public boolean atualizarFornecedor(String nomeOriginal, FornecedorMaterial fornecedor) throws SQLException {
        return fornecedorMaterialDAO.atualizar(nomeOriginal, fornecedor);
    }

    public boolean excluirFornecedor(String nome) throws SQLException {
        return fornecedorMaterialDAO.excluir(nome);
    }

    public List<FornecedorMaterial> pesquisarFornecedores(String nome) throws SQLException {
        return fornecedorMaterialDAO.pesquisarPorNome(nome);
    }

    public List<FornecedorMaterial> listarTodosFornecedores() throws SQLException {
        return fornecedorMaterialDAO.listarTodos();
    }
}