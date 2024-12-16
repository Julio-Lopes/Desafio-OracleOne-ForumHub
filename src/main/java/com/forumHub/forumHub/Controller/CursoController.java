package com.forumHub.forumHub.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forumHub.forumHub.Model.Curso;
import com.forumHub.forumHub.Service.CursoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/curso")
@SecurityRequirement(name = "bearer-key")
public class CursoController {
    
    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<Curso> criaCurso(@RequestBody Curso curso) {
        Curso cursoCriado = cursoService.criaCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoCriado);
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<Curso>> listaCurso() {
        List<Curso> cursos = cursoService.listaCurso();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> listaCurso(@PathVariable long id) {
        Curso curso = cursoService.listaCursoPorid(id);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaCurso(@PathVariable Long id){
        if(id != null){
            cursoService.removerCurso(id);
            return ResponseEntity.ok("Curso apagado com sucesso");
        }
        return ResponseEntity.badRequest().body("Erro ao apagar o curso");
    }
    
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> atualizarCurso(@PathVariable long id, @RequestBody Curso curso) {
        if(cursoService.atualizarResposta(id, curso) == false) return ResponseEntity.badRequest().body("Erro ao atualziar o curso");
        return ResponseEntity.ok("O curso foi alterado com sucesso");
    }
}
