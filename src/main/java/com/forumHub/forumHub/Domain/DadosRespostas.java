package com.forumHub.forumHub.Domain;


public record DadosRespostas(String mensagem, String solucao) {

    public record DadosRepostas1(Long id, String solucao, String mensagem, String autor){}
}
