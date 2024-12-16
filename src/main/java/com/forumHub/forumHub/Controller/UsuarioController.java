package com.forumHub.forumHub.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forumHub.forumHub.Domain.UsuarioDTO;
import com.forumHub.forumHub.Service.RegistrarService;


@RestController
@RequestMapping("/registrar")
public class UsuarioController {

    @Autowired
    private RegistrarService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody UsuarioDTO usuario) {
        try {
            usuarioService.criaUsuario(usuario); 
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
        }
    }
}