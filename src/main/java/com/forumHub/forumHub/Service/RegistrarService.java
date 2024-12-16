package com.forumHub.forumHub.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.forumHub.forumHub.Domain.UsuarioDTO;
import com.forumHub.forumHub.Model.Perfil;
import com.forumHub.forumHub.Model.Usuario;
import com.forumHub.forumHub.Repository.UsuarioRepository;

@Service
public class RegistrarService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario criaUsuario(UsuarioDTO usuario){
        Usuario usuarioCriar = new Usuario();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        usuarioCriar.setNome(usuario.nome());
        usuarioCriar.setEmail(usuario.email());
        usuarioCriar.setSenha(passwordEncoder.encode(usuario.senha()));
        usuarioCriar.setPerfil(new Perfil(1, "USER"));

        return usuarioRepository.save(usuarioCriar);
    }

}
