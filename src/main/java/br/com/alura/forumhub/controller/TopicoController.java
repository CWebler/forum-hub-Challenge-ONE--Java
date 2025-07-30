package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.dto.DadosCadastroTopico;
import br.com.alura.forumhub.dto.DadosDetalhamentoTopico;
import br.com.alura.forumhub.dto.DadosListagemTopico;
import br.com.alura.forumhub.modelo.Curso;
import br.com.alura.forumhub.modelo.Topico;
import br.com.alura.forumhub.modelo.Usuario;
import br.com.alura.forumhub.repository.CursoRepository;
import br.com.alura.forumhub.repository.TopicoRepository;
import br.com.alura.forumhub.repository.UsuarioRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastrarTopico(@RequestBody @Valid DadosCadastroTopico dados) {
        boolean existe = topicoRepository.existsByTituloAndMensagem(dados.getTitulo(), dados.getMensagem());
        if (existe) {
            return ResponseEntity.badRequest().body("Tópico com título e mensagem já existe");
        }

        Usuario autor = usuarioRepository.findByEmail(dados.getAutor());
        if (autor == null) {
            return ResponseEntity.badRequest().body("Autor não encontrado.");
        }

        Curso curso = null;
        if (dados.getCurso() != null && !dados.getCurso().isBlank()) {
            curso = cursoRepository.findByNome(dados.getCurso());
            if (curso == null) {
                return ResponseEntity.badRequest().body("Curso informado não encontrado.");
            }
        }

        Topico topico = new Topico();
        topico.setTitulo(dados.getTitulo());
        topico.setMensagem(dados.getMensagem());
        topico.setCurso(curso);
        topico.setAutor(autor);

        topicoRepository.save(topico);

        return ResponseEntity.ok(topico);
    }

    @GetMapping
    public Page<DadosListagemTopico> listarTopicos(
            @RequestParam(required = false) String curso,
            @PageableDefault(size = 10, sort = "dataCriacao") Pageable paginacao) {

        Page<Topico> topicos;

        if (curso != null) {
            topicos = topicoRepository.findByCursoNome(curso, paginacao);
        } else {
            topicos = topicoRepository.findAll(paginacao);
        }

        return topicos.map(DadosListagemTopico::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalharTopico(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topicoOptional.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarTopico(@PathVariable Long id, @RequestBody @Valid DadosCadastroTopico dados) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario autor = usuarioRepository.findByEmail(dados.getAutor());
        if (autor == null) {
            return ResponseEntity.badRequest().body("Autor não encontrado.");
        }

        Curso curso = null;
        if (dados.getCurso() != null && !dados.getCurso().isBlank()) {
            curso = cursoRepository.findByNome(dados.getCurso());
            if (curso == null) {
                return ResponseEntity.badRequest().body("Curso informado não encontrado.");
            }
        }

        Topico topico = topicoOptional.get();
        topico.setTitulo(dados.getTitulo());
        topico.setMensagem(dados.getMensagem());
        topico.setCurso(curso);
        topico.setAutor(autor);

        topicoRepository.save(topico);

        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirTopico(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
