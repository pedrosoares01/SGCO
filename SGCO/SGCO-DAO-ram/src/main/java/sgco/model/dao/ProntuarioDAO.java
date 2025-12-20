package sgco.model.dao;

import sgco.sgco.domain.Prontuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProntuarioDAO {
    public void cadastrar(Prontuario prontuario) throws Exception{
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st;
        String sql = "INSERT INTO prontuario (nome, ultimo_exame, profissional, endereco, telefone, naturalidade, profissao, nascimento, estado_civil, indicacao, convenio, sexo, QP, HDA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        st = conn.prepareStatement(sql);
        st.setString(1, prontuario.getNome());
        st.setString(2, prontuario.getUltimo_exame());
        st.setString(3, prontuario.getProfissional());
        st.setString(4, prontuario.getEndereco());
        st.setString(5, prontuario.getTelefone());
        st.setString(6, prontuario.getNaturalidade());
        st.setString(7, prontuario.getProfissao());
        st.setString(8, prontuario.getNascimento());
        st.setString(9, prontuario.getEstado_civil());
        st.setString(10, prontuario.getIndicacao());
        st.setString(11, prontuario.getConvenio());
        st.setString(12, prontuario.getSexo().toString());
        st.setString(13, prontuario.getQp());
        st.setString(14, prontuario.getHda());
        st.executeUpdate();
        st.close();
        conn.close();
    }

    public List<Prontuario> pesquisar(Prontuario prontuario) throws Exception {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st;
        String sql = "SELECT * FROM prontuario WHERE nome LIKE ? OR profissional LIKE ? ORDER BY ultimo_exame";
        st = conn.prepareStatement(sql);
        st.setString(1, "%" + prontuario.getNome() + "%");
        st.setString(2, "%" + prontuario.getProfissional() + "%");
        ResultSet rs = st.executeQuery();
        List<Prontuario> lista = new ArrayList<>();
        while (rs.next()) {
            Prontuario p = new Prontuario();
            p.setNome(rs.getString("nome"));
            p.setUltimo_exame(rs.getString("ultimo_exame"));
            p.setProfissional(rs.getString("profissional"));
            p.setEndereco(rs.getString("endereco"));
            p.setTelefone(rs.getString("telefone"));
            p.setNaturalidade(rs.getString("naturalidade"));
            p.setProfissao(rs.getString("profissao"));
            p.setNascimento(rs.getString("nascimento"));
            p.setEstado_civil(rs.getString("estado_civil"));
            p.setIndicacao(rs.getString("indicacao"));
            p.setConvenio(rs.getString("convenio"));
            p.setSexo(rs.getString("sexo").charAt(0));
            p.setQp(rs.getString("qp"));
            p.setHda(rs.getString("hda"));
            p.setId(rs.getInt("id"));
            lista.add(p);
        }
        if (lista.isEmpty())
            throw new Exception("Nenhuma correspondecia");
        rs.close();
        st.close();
        conn.close();
        return lista;
    }
    public void excluir(int id) throws Exception{
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st;
        String sql = "DELETE FROM prontuario WHERE id = ?";
        st = conn.prepareStatement(sql);
        st.setInt(1, id);
        st.executeUpdate();
        st.close();
        conn.close();
    }
    public Prontuario pesquisarId(int id) throws Exception{
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st;
        String sql = "SELECT * FROM prontuario WHERE id = ?";
        st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        Prontuario prontuario;
        if(rs.next()){
            prontuario = new Prontuario();
            prontuario.setNome(rs.getString("nome"));
            prontuario.setUltimo_exame(rs.getString("ultimo_exame"));
            prontuario.setProfissional(rs.getString("profissional"));
            prontuario.setEndereco(rs.getString("endereco"));
            prontuario.setTelefone(rs.getString("telefone"));
            prontuario.setNaturalidade(rs.getString("naturalidade"));
            prontuario.setProfissao(rs.getString("profissao"));
            prontuario.setNascimento(rs.getString("nascimento"));
            prontuario.setEstado_civil(rs.getString("estado_civil"));
            prontuario.setIndicacao(rs.getString("indicacao"));
            prontuario.setConvenio(rs.getString("convenio"));
            prontuario.setSexo(rs.getString("sexo").charAt(0));
            prontuario.setQp(rs.getString("qp"));
            prontuario.setHda(rs.getString("hda"));
            prontuario.setId(rs.getInt("id"));
        } else {
            throw new Exception("Nenhuma correspondecia");
        }
        rs.close();
        st.close();
        conn.close();
        return prontuario;
    }
    public void atualizar(Prontuario prontuario) throws Exception{
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st;
        String sql = "UPDATE prontuario SET nome = ?, ultimo_exame = ?, profissional = ?, endereco = ?, telefone = ?, naturalidade = ?, profissao= ?, nascimento = ?, estado_civil = ?, indicacao = ?, convenio = ?, sexo = ?, QP = ?, HDA = ? WHERE id = ?";
        st = conn.prepareStatement(sql);
        st.setInt(15, prontuario.getId());
        st.setString(1, prontuario.getNome());
        st.setString(2, prontuario.getUltimo_exame());
        st.setString(3, prontuario.getProfissional());
        st.setString(4, prontuario.getEndereco());
        st.setString(5, prontuario.getTelefone());
        st.setString(6, prontuario.getNaturalidade());
        st.setString(7, prontuario.getProfissao());
        st.setString(8, prontuario.getNascimento());
        st.setString(9, prontuario.getEstado_civil());
        st.setString(10, prontuario.getIndicacao());
        st.setString(11, prontuario.getConvenio());
        st.setString(12, prontuario.getSexo().toString());
        st.setString(13, prontuario.getQp());
        st.setString(14, prontuario.getHda());
        st.executeUpdate();
        st.close();
        conn.close();
    }
}
