package com.projetopoo.mytickets.model.DTOs;

import java.time.LocalDateTime;

public record SugestaoDTO(
    Long idSugestao,
    String nomeEvento,
    String descricao,
    Long criadorId,
    LocalDateTime dataHora
) {}
