package com.projetopoo.mytickets.model.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record VisitaDTO(
        @NotNull(message = "A data da visita é obrigatória")
        @Future(message = "A visita deve ser agendada para uma data futura")
        LocalDateTime scheduledAt,

        @NotNull(message = "O ID do solicitante é obrigatório")
        Long requesterId,

        Long authorizerId
) {}
