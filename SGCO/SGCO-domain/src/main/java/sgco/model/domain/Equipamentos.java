package sgco.model.domain;

public class Equipamentos {

    private int id;
    private String nome;
    private String codigo;
    private String local;
    private String ultima;
    private int freq;
    private String status;

    public Equipamentos() {}

    public Equipamentos(String nome, String codigo, String local, String ultima, int freq, String status) {
        this.nome = nome;
        this.codigo = codigo;
        this.local = local;
        this.ultima = ultima;
       	this.freq = freq;
        this.status = status;
    }

    public Equipamentos(int id, String nome, String codigo, String local, String ultima, int freq, String status) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.local = local;
        this.ultima = ultima;
        this.freq = freq;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public String getUltima() { return ultima; }
    public void setUltima(String ultima) { this.ultima = ultima; }

    public int getFreq() { return freq; }
    public void setFreq(int freq) { this.freq = freq; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
