package com.projetopoo.mytickets.model.dtos;

import java.time.LocalDateTime;

public record AgendamentoResponseDTO(
        Long idAgendamento,
        LocalDateTime bookedAt,
        String userName,
        String eventName,
        int personCount
) {}
