package com.projetopoo.mytickets.model.dtos;

import com.projetopoo.mytickets.model.enums.EventCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SugestaoDTO(
        @NotBlank(message = "O nome do evento é obrigatório")
        @Size(min = 5, max = 50, message = "O nome deve ter entre 5 e 50 caracteres")
        String eventName,

        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String description,

        EventCategory category
) {}
