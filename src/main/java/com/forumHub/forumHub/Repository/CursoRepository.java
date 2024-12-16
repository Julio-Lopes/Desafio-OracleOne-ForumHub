package com.forumHub.forumHub.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forumHub.forumHub.Model.Curso;



@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{
    Curso findByNome(String nome);
    Curso findById(long id);
}
