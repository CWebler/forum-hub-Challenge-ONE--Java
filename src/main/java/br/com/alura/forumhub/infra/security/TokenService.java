package br.com.alura.forumhub.infra.security;

import br.com.alura.forumhub.modelo.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.security.Key;
import java.nio.charset.StandardCharsets;

@Service
public class TokenService {

    private final String secret = "minha-chave-secreta-super-secreta-que-tem-mais-de-32-caracteres";
    private final long expiration = 3600000; // 1 hora

    public String gerarToken(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();

        Date agora = new Date();
        Date expiracao = new Date(agora.getTime() + expiration);

        Key chaveAssinatura = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setIssuer("API FórumHub")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(agora)
                .setExpiration(expiracao)
                .signWith(chaveAssinatura, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getSubject(String tokenJWT) {
        Key chaveAssinatura = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(chaveAssinatura)
                .build()
                .parseClaimsJws(tokenJWT)
                .getBody()
                .getSubject();
    }
}
