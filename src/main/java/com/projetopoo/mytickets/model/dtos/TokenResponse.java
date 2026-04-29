package com.projetopoo.mytickets.model.dtos;

public record TokenResponse(
        String token,
        Long idUsuario,
        String name,
        String email,
        String username,
        String role
) {}
