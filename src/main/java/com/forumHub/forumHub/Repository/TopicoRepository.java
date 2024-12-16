package com.forumHub.forumHub.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forumHub.forumHub.Model.Topico;


@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long>{
    Topico findById(long id);
}
