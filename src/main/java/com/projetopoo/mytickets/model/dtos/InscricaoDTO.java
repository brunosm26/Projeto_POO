package com.projetopoo.mytickets.model.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public record InscricaoDTO(
        @NotNull(message = "O ID do usuário é obrigatório")
        Long userId,

        @NotNull(message = "O ID do evento é obrigatório")
        Long eventId,

        LocalDateTime registrationAt,

        @Positive(message = "A quantidade de visitantes deve ser maior que zero")
        int visitorCount
) {}
