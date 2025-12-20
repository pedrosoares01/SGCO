package sgco.sgco.domain;

public class FornecedorServico {
    private String nomeServico;
    private String fornecedor;
    private String contato;

    public FornecedorServico() {
    }

    public FornecedorServico(String nomeServico, String fornecedor, String contato) {
        this.nomeServico = nomeServico;
        this.fornecedor = fornecedor;
        this.contato = contato;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}