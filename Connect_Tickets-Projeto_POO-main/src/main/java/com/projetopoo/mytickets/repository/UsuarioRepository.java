package com.projetopoo.mytickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetopoo.mytickets.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    

}
