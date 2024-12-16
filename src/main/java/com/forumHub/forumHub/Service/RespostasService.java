package com.forumHub.forumHub.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forumHub.forumHub.Domain.DadosRespostas;
import com.forumHub.forumHub.Infra.Security.TokenService;
import com.forumHub.forumHub.Model.Resposta;
import com.forumHub.forumHub.Model.Topico;
import com.forumHub.forumHub.Model.Usuario;
import com.forumHub.forumHub.Repository.RespostaRepository;
import com.forumHub.forumHub.Repository.TopicoRepository;
import com.forumHub.forumHub.Repository.UsuarioRepository;

@Service
public class RespostasService {
    
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired 
    private RespostaRepository respostaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    public Resposta criarResposta(DadosRespostas dadosRespostas, String token, long id){
        Resposta resposta = new Resposta();
        resposta.setMensagem(dadosRespostas.mensagem());
        resposta.setSolucao(dadosRespostas.solucao());
        resposta.setDataCriacao(LocalDateTime.now());

        Topico topico = topicoRepository.findById(id);
        resposta.setTopico(topico);

        String email = tokenService.getSubject(token);

        Usuario autor = (Usuario) usuarioRepository.findByEmail(email);
        resposta.setAutor(autor);

        return respostaRepository.save(resposta);
    }

    public List<Resposta> listarRespostasDeUmTopico(long id){
        Topico topico = topicoRepository.findById(id);
        return respostaRepository.findByTopico(topico);
    }

    public Boolean deletaResposta(long id, String token){
        Resposta resposta = respostaRepository.findById(id);
        String email = tokenService.getSubject(token);
        Usuario autor = (Usuario) usuarioRepository.findByEmail(email);

        if(resposta.getAutor().getId() == autor.getId()){
            respostaRepository.deleteById(id);
            return true;
        }
        
        return false;
    }

    public Boolean atualizarResposta(long id, DadosRespostas dados, String token){
        Resposta resposta = respostaRepository.findById(id);
        String email = tokenService.getSubject(token);
        Usuario autor = (Usuario) usuarioRepository.findByEmail(email);

        if(resposta.getAutor().getId() == autor.getId()){
            resposta.atualizarResposta(dados);
            return true;
        }

        return false;
    }
}
