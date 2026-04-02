package com.projetopoo.mytickets.model.DTOs;

import java.time.LocalDateTime;

public record AgendamentoDTO(
    Long idAgendamento,
    LocalDateTime dataHoraAgendamento,
    Long usuarioId,
    Long eventoId,
    int qntdPessoas
) {}
