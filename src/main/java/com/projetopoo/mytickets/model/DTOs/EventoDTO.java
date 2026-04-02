package com.projetopoo.mytickets.model.DTOs;

import java.time.LocalDateTime;

public record EventoDTO(
    Long idEvento,
    String nomeEvento,
    String descricao,
    int capacidade,
    Boolean gratuito,
    LocalDateTime dataHora,
    double preco,
    int expectedPublic,
    Boolean isFimDeSemana,
    Long tipoEventoId,
    Long criadorId
) {}
