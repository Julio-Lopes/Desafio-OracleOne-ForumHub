package com.forumHub.forumHub.Domain;

import java.time.LocalDateTime;

public record RespostaDTO(Long id, String solucao, String mensagem, LocalDateTime dataCriacao) {
    
}
