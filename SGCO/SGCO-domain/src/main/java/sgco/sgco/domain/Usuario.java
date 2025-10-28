package sgco.sgco.domain;

import sgco.sgco.domain.util.UsuarioTipo;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private UsuarioTipo cargo;
    
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
    
    public void setCargo(UsuarioTipo cargo){
        this.cargo = cargo;
    }
    
    public UsuarioTipo getCargo(){
        return cargo;
    }
}
