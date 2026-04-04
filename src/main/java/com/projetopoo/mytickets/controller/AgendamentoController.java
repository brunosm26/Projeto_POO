package com.projetopoo.mytickets.controller;

import com.projetopoo.mytickets.model.dtos.AgendamentoDTO;
import com.projetopoo.mytickets.model.dtos.AgendamentoResponseDTO;
import com.projetopoo.mytickets.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<AgendamentoResponseDTO> listar() {
        return service.listarAgendamentos().stream()
                .map(service::toResponseDTO)
                .toList();
    }

    @GetMapping("/{idAgendamento}")
    public AgendamentoResponseDTO buscarPorId(@PathVariable Long idAgendamento) {
        return service.toResponseDTO(service.buscarPorId(idAgendamento));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoResponseDTO criar(@Valid @RequestBody AgendamentoDTO dto) {
        return service.toResponseDTO(service.criarAgendamento(dto));
    }
}
