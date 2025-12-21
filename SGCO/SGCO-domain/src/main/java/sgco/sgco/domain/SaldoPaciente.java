package sgco.sgco.domain;

public class SaldoPaciente {
    private String nomeDevedor;
    private double pago;
    private double devido;
    private double totalPagar;
    private int pacienteId;
    private int orcamentoId;

    public String getNomeDevedor() {
        return nomeDevedor;
    }

    public void setNomeDevedor(String nomeDevedor) {
        this.nomeDevedor = nomeDevedor;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public double getDevido() {
        return devido;
    }

    public void setDevido(double devido) {
        this.devido = devido;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public int getOrcamentoId() {
        return orcamentoId;
    }

    public void setOrcamentoId(int orcamentoId) {
        this.orcamentoId = orcamentoId;
    }
}
