package sgco.model.domain;

import java.time.LocalDate;

public class NotaFiscal {

    private int id;
    private int orcamentoId;
    private double valorTotal;
    private LocalDate dataEmissao;
    private String nomePaciente;
    private String nomeProfissional;

    public NotaFiscal() {
    }

    public NotaFiscal(int orcamentoId, double valorTotal, LocalDate dataEmissao,
                      String nomePaciente, String nomeProfissional) {
        this.orcamentoId = orcamentoId;
        this.valorTotal = valorTotal;
        this.dataEmissao = dataEmissao;
        this.nomePaciente = nomePaciente;
        this.nomeProfissional = nomeProfissional;
    }

    public NotaFiscal(int id, int orcamentoId, double valorTotal, LocalDate dataEmissao,
                      String nomePaciente, String nomeProfissional) {
        this.id = id;
        this.orcamentoId = orcamentoId;
        this.valorTotal = valorTotal;
        this.dataEmissao = dataEmissao;
        this.nomePaciente = nomePaciente;
        this.nomeProfissional = nomeProfissional;
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

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }
}
