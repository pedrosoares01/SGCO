package sgco.sgco.domain;

public class Agenda {
    private String paciente, profissional, data, hora;
    private int id;
    public String getPaciente() {
        return paciente;
    }
    public String getProfissional() {
        return profissional;
    }
    public String getData() {
        return data;
    }
    public String getHora() {
        return hora;
    }
    public int getId() {
        return id;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }
    public void setProfissional(String profissional) {
        this.profissional = profissional;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public void setId(int id) {
        this.id = id;
    }
}
