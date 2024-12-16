package com.forumHub.forumHub.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forumHub.forumHub.Domain.DadosRespostas;
import com.forumHub.forumHub.Domain.DadosRespostas.DadosRepostas1;
import com.forumHub.forumHub.Domain.DadosTopico;
import com.forumHub.forumHub.Domain.RespostaDTO;
import com.forumHub.forumHub.Domain.TopicoDTO;
import com.forumHub.forumHub.Domain.TopicoDadosAtualizar;
import com.forumHub.forumHub.Domain.TopicoDTO.TopicoDTO1;
import com.forumHub.forumHub.Model.Resposta;
import com.forumHub.forumHub.Model.Topico;
import com.forumHub.forumHub.Service.RespostasService;
import com.forumHub.forumHub.Service.TopicoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    
    @Autowired
    private TopicoService topicoService;

    @Autowired
    private RespostasService respostasService;

    @PostMapping
    public ResponseEntity<TopicoDTO> criarTopico(@RequestBody DadosTopico DadosTopico,
                                              @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");

        Topico topico = topicoService.criarTopico(DadosTopico, token);
        TopicoDTO topicoDTO = new TopicoDTO(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao());
        return ResponseEntity.status(HttpStatus.CREATED).body(topicoDTO);
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<TopicoDTO1>> listaTopico() {
        List<Topico> topicos = topicoService.listaTopico();

        List<TopicoDTO1> topicosDTO = topicos.stream()
            .map(topico -> new TopicoDTO1(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getRespostas().stream().map(resposta -> new DadosRepostas1(resposta.getId(), resposta.getMensagem(), 
                                                                    resposta.getSolucao(), resposta.getAutor().getNome())).collect(Collectors.toList())
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(topicosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listaTopicoPorId(@PathVariable long id) {
        if (id <= 0) { 
            return ResponseEntity.badRequest().body("O ID deve ser um número positivo.");
        }

        Topico topico = topicoService.listaTopicoPorId(id);
            
        if (topico == null) {
            return ResponseEntity.notFound().build(); 
        }

        TopicoDTO1 topicoDTO = new TopicoDTO1(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(),
        topico.getRespostas().stream().map(resposta -> new DadosRepostas1(resposta.getId(), resposta.getMensagem(),
                                                             resposta.getSolucao(), resposta.getAutor().getNome())).collect(Collectors.toList()));
    
        return ResponseEntity.ok(topicoDTO);

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> atualizarTopico(@PathVariable Long id, @RequestBody TopicoDadosAtualizar dados, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        if(topicoService.atualizarTopico(id, dados, token) == false) return ResponseEntity.badRequest().body("O topico só pode ser alterado pelo proprietario");
        return ResponseEntity.ok("O topico foi alterado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTopico(@PathVariable Long id, @RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");
        if(topicoService.deletaTopico(id, token) == false) return ResponseEntity.badRequest().body("O topico só pode ser removido pelo proprietario");
        return ResponseEntity.ok("O topico foi removido com sucesso");
    }

    @PostMapping("/{id}/respostas")
    public ResponseEntity<DadosRespostas> criarResposta( @PathVariable Long id,
                                                    @RequestBody DadosRespostas DadosRespostas,
                                                    @RequestHeader("Authorization") String authorizationHeader
                                                    ) {
        String token = authorizationHeader.replace("Bearer ", "");

        Resposta resposta = respostasService.criarResposta(DadosRespostas, token, id);
        DadosRespostas respostaDTO = new DadosRespostas(resposta.getMensagem(), resposta.getSolucao());
        return ResponseEntity.status(HttpStatus.CREATED).body(respostaDTO);
    }
    
    @GetMapping("/{id}/respostas")
    public ResponseEntity<List<RespostaDTO>> listarRespostasDeUmTopico(@PathVariable Long id) {
        List<Resposta> respostas = respostasService.listarRespostasDeUmTopico(id);

        List<RespostaDTO> respostaDTO = respostas.stream()
            .map(resposta -> new RespostaDTO(
                resposta.getId(),
                resposta.getSolucao(),
                resposta.getMensagem(),
                resposta.getDataCriacao()
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(respostaDTO);
    }

    @DeleteMapping("/{id}/respostas/{idResposta}")
    @Transactional
    public ResponseEntity<String> deleteResposta(@PathVariable Long id, @PathVariable long idResposta, @RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");
        if(respostasService.deletaResposta(idResposta, token) == false) return ResponseEntity.badRequest().body("A resposta só pode ser removida pelo proprietario");
        return ResponseEntity.ok("A resposta foi apagado com sucesso");
    }

    @PutMapping("/{id}/respostas/{idResposta}")
    @Transactional
    public ResponseEntity<String> atualizarResposta(@PathVariable Long id, @RequestBody DadosRespostas dados, @PathVariable long idResposta, 
                                                        @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        if(respostasService.atualizarResposta(idResposta, dados, token) == false) return ResponseEntity.badRequest().body("A resposta só pode ser alterada pelo proprietario");
        return ResponseEntity.ok("A resposta foi alterada com sucesso");
    }
}