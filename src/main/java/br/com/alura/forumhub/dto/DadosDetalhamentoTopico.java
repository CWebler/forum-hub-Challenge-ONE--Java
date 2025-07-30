package br.com.alura.forumhub.dto;

import br.com.alura.forumhub.modelo.Topico;
import br.com.alura.forumhub.modelo.StatusTopico;

import java.time.LocalDateTime;

public class DadosDetalhamentoTopico {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private StatusTopico status;
    private String autor;
    private String curso;

    public DadosDetalhamentoTopico(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.status = topico.getStatus();
        this.autor = topico.getAutor().getNome();
        this.curso = topico.getCurso().getNome();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }
}
