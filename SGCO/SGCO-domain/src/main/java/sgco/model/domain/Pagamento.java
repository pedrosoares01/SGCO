package sgco.model.domain;

import java.time.LocalDate;

public class Pagamento {

    private int id;
    private int orcamentoId;
    private String formaPagamento;
    private double valorPago;
    private LocalDate dataPagamento;

    public Pagamento() {}

    public Pagamento(int orcamentoId, String formaPagamento, double valorPago, LocalDate dataPagamento) {
        this.orcamentoId = orcamentoId;
        this.formaPagamento = formaPagamento;
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
    }

    public Pagamento(int id, int orcamentoId, String formaPagamento, double valorPago, LocalDate dataPagamento) {
        this.id = id;
        this.orcamentoId = orcamentoId;
        this.formaPagamento = formaPagamento;
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getOrcamentoId() {
        return orcamentoId;
    }
    public void setOrcamentoId(int orcamentoId) {
        this.orcamentoId = orcamentoId;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getValorPago() {
        return valorPago;
    }
    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }
    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
