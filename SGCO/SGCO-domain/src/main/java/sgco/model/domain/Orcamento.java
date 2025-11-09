package sgco.model.domain;

public class Orcamento {

    private int id;
    private String descricao;
    private double valor;
    private int idProfissional;
    private int idPaciente;

    public Orcamento() {
    }

    public Orcamento(int id, String descricao, double valor, int idProfissional, int idPaciente) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.idProfissional = idProfissional;
        this.idPaciente = idPaciente;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
}
