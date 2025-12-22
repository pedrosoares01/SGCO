package sgco.model.domain;

import java.math.BigDecimal;
import java.sql.Date;

public class Despesa {
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getData() {
        return dataDespesa;
    }

    public void setData(Date dataDespesa) {
        this.dataDespesa = dataDespesa;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    String descricao, formaPagamento;
    Date dataDespesa;
    BigDecimal valor;
}
