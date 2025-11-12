package sgco.model.domain;

public class Procedimento {
    private int id;
    private String nome;
    private double preco;

    public Procedimento() {}

    public Procedimento(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Procedimento(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
}