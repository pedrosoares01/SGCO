package model.domain;

import java.math.BigDecimal;

public class Despesa {
    private int id;
    private String descricao;
    private BigDecimal valor;
    private String pagamento;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getPagamento() { return pagamento; }
    public void setPagamento(String pagamento) { this.pagamento = pagamento; }
}
