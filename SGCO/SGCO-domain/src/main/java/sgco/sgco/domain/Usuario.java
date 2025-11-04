package sgco.sgco.domain;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private String cargo;
    private int id;
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return email;
    }
    
     public void setSenha(String senha){
        this.senha = senha;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public void setCargo(String cargo){
        this.cargo = cargo;
    }
    
    public String getCargo(){
        return cargo;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
}
