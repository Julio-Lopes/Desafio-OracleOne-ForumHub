package com.forumHub.forumHub.Model;

import java.time.LocalDateTime;

import com.forumHub.forumHub.Domain.DadosRespostas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String mensagem;

    @ManyToOne 
    @JoinColumn(name = "topico", nullable = false)
    private Topico topico;

    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "autor", nullable = false)
    private Usuario autor;

    private String solucao;

        public void atualizarResposta(DadosRespostas dados) {
        if(dados.mensagem() != null) this.mensagem = dados.mensagem();
        if(dados.solucao() != null) this.solucao = dados.solucao();
    }

}