package com.projetopoo.mytickets.model.dtos;

import com.projetopoo.mytickets.model.enums.EventCategory;
import com.projetopoo.mytickets.model.enums.SuggestionStatus;

public record SugestaoResponseDTO(
        Long idSugestao,
        String eventName,
        String description,
        EventCategory category,
        SuggestionStatus status,
        String creatorName
) {}
