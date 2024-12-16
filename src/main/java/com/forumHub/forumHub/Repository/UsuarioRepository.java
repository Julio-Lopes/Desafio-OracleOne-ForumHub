package com.forumHub.forumHub.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.forumHub.forumHub.Model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{


    UserDetails findByEmail(String email);
    
}
