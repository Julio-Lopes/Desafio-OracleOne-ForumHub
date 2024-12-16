package com.forumHub.forumHub.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forumHub.forumHub.Model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>{
    
}
