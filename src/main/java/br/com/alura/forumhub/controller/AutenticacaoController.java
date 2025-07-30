package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.dto.DadosAutenticacao;
import br.com.alura.forumhub.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> autenticar(@RequestBody @Valid DadosAutenticacao dados) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(dados.getEmail(), dados.getSenha());

        Authentication authentication = authenticationManager.authenticate(token);
        String jwt = tokenService.gerarToken(authentication);

        return ResponseEntity.ok(jwt);
    }
}
