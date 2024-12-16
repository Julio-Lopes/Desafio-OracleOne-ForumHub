package com.forumHub.forumHub.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forumHub.forumHub.Domain.DadosTopico;
import com.forumHub.forumHub.Domain.TopicoDadosAtualizar;
import com.forumHub.forumHub.Infra.Security.TokenService;
import com.forumHub.forumHub.Model.Curso;
import com.forumHub.forumHub.Model.Topico;
import com.forumHub.forumHub.Model.Usuario;
import com.forumHub.forumHub.Repository.CursoRepository;
import com.forumHub.forumHub.Repository.TopicoRepository;
import com.forumHub.forumHub.Repository.UsuarioRepository;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService; 

    public Topico criarTopico(DadosTopico dadosTopico, String token){
        Topico topico = new Topico();
        topico.setTitulo(dadosTopico.titulo());
        topico.setMensagem(dadosTopico.mensagem());
        topico.setDataCriacao(LocalDateTime.now());
        topico.setStatus("ABERTO");

        Curso curso = cursoRepository.findByNome(dadosTopico.nomeCurso());
        topico.setCurso(curso);

        String email = tokenService.getSubject(token);

        Usuario autor = (Usuario) usuarioRepository.findByEmail(email);
        topico.setAutor(autor);

        return topicoRepository.save(topico);
    }

    public List<Topico> listaTopico(){
        return topicoRepository.findAll();
    }

    public Topico listaTopicoPorId(long id){
        return topicoRepository.findById(id);
    }

    public Boolean atualizarTopico(long id, TopicoDadosAtualizar dados, String token){
        Topico topico = topicoRepository.findById(id);
        String email = tokenService.getSubject(token);
        Usuario autor = (Usuario) usuarioRepository.findByEmail(email);

        if(topico.getAutor().getId() == autor.getId()){
            topico.atualizarTopico(dados);
            return true;
        }

        return false;
    }

    public Boolean deletaTopico(long id, String token){
        Topico topico = topicoRepository.findById(id);
        String email = tokenService.getSubject(token);
        Usuario autor = (Usuario) usuarioRepository.findByEmail(email);

        if(topico.getAutor().getId() == autor.getId()){
            topicoRepository.deleteById(id);
            return true;
        }
        
        return false;
    }

}