package com.projetopoo.mytickets.model.dtos;

import java.time.LocalDateTime;

public record VisitaResponseDTO(
        Long idVisita,
        LocalDateTime scheduledAt,
        Boolean isAuthorized,
        String requesterName,
        String authorizerName
) {}
