package com.forumHub.forumHub.Model;

import java.time.LocalDateTime;
import java.util.List;

import com.forumHub.forumHub.Domain.TopicoDadosAtualizar;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String status;
    @ManyToOne
    @JoinColumn(name = "autor", nullable = false)
    private Usuario autor;

    @OneToMany(mappedBy = "topico") 
    private List<Resposta> respostas;

    @ManyToOne
    @JoinColumn(name = "curso", nullable = false)
    private Curso curso;

    public void atualizarTopico(TopicoDadosAtualizar dados) {
        if(dados.titulo() != null) this.titulo = dados.titulo();
        if(dados.mensagem() != null) this.mensagem = dados.mensagem();
    }
    
}