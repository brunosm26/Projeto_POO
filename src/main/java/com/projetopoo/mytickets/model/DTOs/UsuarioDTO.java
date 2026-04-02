package com.projetopoo.mytickets.model.DTOs;

public record UsuarioDTO(
    Long id,
    String nome,
    String email,
    Boolean isAdmin,
    String username
) {}
