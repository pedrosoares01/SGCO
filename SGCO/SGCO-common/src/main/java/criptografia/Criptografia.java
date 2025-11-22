package criptografia;

import org.mindrot.jbcrypt.BCrypt;

public class Criptografia {
    private static int workload = 8;

    public static String criptografar(String senha) {
        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(senha, salt);
    }

    public static boolean verificarSenha(String senhaInserida, String senhaCriptografada) {
        return  BCrypt.checkpw(senhaInserida, senhaCriptografada);
    }


}
