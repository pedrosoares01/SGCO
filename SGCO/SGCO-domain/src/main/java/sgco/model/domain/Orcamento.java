package sgco.model.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Orcamento {

    private int id;
    private String descricao;
    private double valor;
    private int idProfissional;
    private int idPaciente;
    private List<Integer> idsProcedimentos; 
    private String nomeProfissional;
    private String nomePaciente;

    public Orcamento() {}

    public Orcamento(int id, String descricao, double valor, int idProfissional, int idPaciente, List<Integer> idsProcedimentos) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.idProfissional = idProfissional;
        this.idPaciente = idPaciente;
        this.idsProcedimentos = idsProcedimentos;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public int getIdProfissional() { return idProfissional; }
    public void setIdProfissional(int idProfissional) { this.idProfissional = idProfissional; }

    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }

    public List<Integer> getIdsProcedimentos() { return idsProcedimentos; }
    public void setIdsProcedimentos(List<Integer> idsProcedimentos) { this.idsProcedimentos = idsProcedimentos; }

    public String getNomeProfissional() { return nomeProfissional; }
    public void setNomeProfissional(String nomeProfissional) { this.nomeProfissional = nomeProfissional; }

    public String getNomePaciente() { return nomePaciente; }
    public void setNomePaciente(String nomePaciente) { this.nomePaciente = nomePaciente; }

    public void gerarDescricao(List<String> nomesProcedimentos) {
        this.descricao = nomesProcedimentos.stream()
                .collect(Collectors.joining(", "));
    }
}
