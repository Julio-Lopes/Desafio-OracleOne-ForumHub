package com.forumHub.forumHub.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forumHub.forumHub.Model.Resposta;
import com.forumHub.forumHub.Model.Topico;

import java.util.List;


@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long>{
    
    List<Resposta> findByTopico(Topico topico);
    Resposta findById(long id);
}
