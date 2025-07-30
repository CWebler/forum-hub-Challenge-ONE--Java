package br.com.alura.forumhub.dto;

import br.com.alura.forumhub.modelo.Topico;
import java.time.LocalDateTime;

public class DadosListagemTopico {

    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String status;
    private String autor;
    private String curso;

    public DadosListagemTopico(Topico topico) {
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.status = topico.getStatus().name();
        this.autor = topico.getAutor().getNome();
        this.curso = topico.getCurso().getNome();
    }

    // Getters (ou use Lombok se estiver usando)
    public String getTitulo() { return titulo; }
    public String getMensagem() { return mensagem; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public String getStatus() { return status; }
    public String getAutor() { return autor; }
    public String getCurso() { return curso; }
}
