
package sgco.sgco.domain;

public class FornecedorMaterial {
    private String nome;
    private String contato;
    private String  email;
    
    public FornecedorMaterial(String nome, String contato, String email){
        this.nome = nome;
        this.contato = contato;
        this.email = email;
       
    }

    public FornecedorMaterial() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setNumeroContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
