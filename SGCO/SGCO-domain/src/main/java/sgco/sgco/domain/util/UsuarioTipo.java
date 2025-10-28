package sgco.sgco.domain.util;

/**
 *
 * @author pedro
*/



public enum UsuarioTipo {
    GERENTE,
    RECEPCIONISTA,
    PROFISSIONAL;
    
    public static UsuarioTipo STRto (String tipo){
    
        switch(tipo){
            case "GERENTE":
                return GERENTE;
           
            case "RECEPCIONISTA" :
                return RECEPCIONISTA;
           
            case "PROFISSIONAL" :
                return PROFISSIONAL;
        }        
        
        
        throw new tipoInvalidoException(tipo + "não é válido");
    }
    
}


