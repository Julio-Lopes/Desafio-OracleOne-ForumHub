package com.forumHub.forumHub.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forumHub.forumHub.Model.Curso;
import com.forumHub.forumHub.Repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso criaCurso(Curso curso){
        return cursoRepository.save(curso);
    }

    public List<Curso> listaCurso(){
        List<Curso> curso = cursoRepository.findAll();
        return curso;
    }

    public Curso listaCursoPorid(long id){
        Curso curso = cursoRepository.findById(id);
        return curso;
    }

    public void removerCurso(long id){
        cursoRepository.deleteById(id);
    }

    public Boolean atualizarResposta(long id, Curso dados){
        Curso curso = cursoRepository.findById(id);

        if(curso != null){
            curso.atualizarCurso(dados);
            return true;
        }
        return false;
    }
}
