package com.projetopoo.mytickets.model.DTOs;

import java.time.LocalDateTime;

public record InscricaoDTO(
    Long id,
    Long usuarioId,
    Long eventoId,
    LocalDateTime dataHoraInscricao,
    int quantidadeVisitantes
) {}
