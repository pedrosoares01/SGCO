package sgco.sgco.service;

import sgco.model.dao.MaterialDAO;
import sgco.sgco.domain.Material;
import java.sql.SQLException;
import java.util.List;

public class MaterialService {

    public boolean cadastrarMaterial(Material material) throws SQLException {
        MaterialDAO dao = new MaterialDAO();
        return dao.inserir(material);
    }

    public boolean excluirMaterial(String nome) throws SQLException {
        MaterialDAO dao = new MaterialDAO();
        return dao.excluir(nome);
    }

    public List<Material> pesquisarMateriais(String nome) throws SQLException {
        MaterialDAO dao = new MaterialDAO();
        return dao.pesquisarPorNome(nome);
    }

    public List<Material> listarTodosMateriais() throws SQLException {
        MaterialDAO dao = new MaterialDAO();
        return dao.listarTodos();
    }
}