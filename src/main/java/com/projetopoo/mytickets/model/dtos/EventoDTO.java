package com.projetopoo.mytickets.model.dtos;

import com.projetopoo.mytickets.model.enums.EventCategory;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record EventoDTO(
        Long idEvento,

        @NotBlank(message = "O nome do evento é obrigatório")
        @Size(min = 5, max = 50, message = "O nome deve ter entre 5 e 50 caracteres")
        String eventName,

        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String description,

        @Positive(message = "A capacidade deve ser maior que zero")
        int capacity,

        String locationDetail,

        Long creatorId,

        Boolean isFree,

        @FutureOrPresent(message = "A data do evento deve ser presente ou futura")
        LocalDateTime scheduledAt,

        Long eventTypeId,

        @PositiveOrZero(message = "O preço não pode ser negativo")
        double price,

        @Positive(message = "A expectativa de público deve ser maior que zero")
        int expectedAttendance,

        Boolean isWeekend,

        EventCategory category,

        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String imageUrl
) {}
