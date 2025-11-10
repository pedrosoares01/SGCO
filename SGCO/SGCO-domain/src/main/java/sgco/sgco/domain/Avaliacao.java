package sgco.sgco.domain;

public class Avaliacao {
    private String profissional;
    private Integer nota;
    public String getProfissional() {
        return profissional;
    }
    public Integer getNota() {
        return nota;
    }
    public void setProfissional(String profissional) {
        this.profissional = profissional;
    }
    public void setNota(Integer nota) {
        this.nota = nota;
    }
}
