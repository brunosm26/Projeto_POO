package com.projetopoo.mytickets.model.dtos;

import java.time.LocalDateTime;

public record FiltroEventoTO(
        Boolean isPaid,
        LocalDateTime startsBefore,
        LocalDateTime startsAfter,
        Long eventTypeId
) {}
