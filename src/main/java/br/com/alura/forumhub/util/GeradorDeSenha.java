package br.com.alura.forumhub.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorDeSenha {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode("1741");
        System.out.println("Senha criptografada: " + senhaCriptografada);
    }
}
