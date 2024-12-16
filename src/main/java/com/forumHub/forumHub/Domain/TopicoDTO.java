package com.forumHub.forumHub.Domain;

import java.time.LocalDateTime;
import java.util.List;

import com.forumHub.forumHub.Domain.DadosRespostas.DadosRepostas1;


public record TopicoDTO(Long id, String titulo, String mensagem, LocalDateTime dataCriacao) {

    public record TopicoDTO1(long id, String titulo, String mensagem, LocalDateTime dataCriacao,
            List<DadosRepostas1> Respostas) {}

    
}
