package com.projetopoo.mytickets.model.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public record AgendamentoDTO(
        Long idAgendamento,

        LocalDateTime bookedAt,

        @NotNull(message = "O ID do usuário é obrigatório")
        Long userId,

        @NotNull(message = "O ID do evento é obrigatório")
        Long eventId,

        @Positive(message = "A quantidade de pessoas deve ser maior que zero")
        int personCount
) {}
