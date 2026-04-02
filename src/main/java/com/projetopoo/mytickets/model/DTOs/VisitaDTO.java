package com.projetopoo.mytickets.model.DTOs;

import java.time.LocalDateTime;

public record VisitaDTO(
    Long idVisita,
    LocalDateTime dataHora,
    Boolean isAutorizada,
    Long usuarioSolicitanteId,
    Long adminAutorizadorId
) {}
