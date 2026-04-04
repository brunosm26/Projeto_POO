package com.projetopoo.mytickets.model.dtos;

import java.time.LocalDateTime;

public record UsuarioResponse(
        Long idUsuario,
        String name,
        String email,
        String username,
        LocalDateTime updatedAt
) {}
