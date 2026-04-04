package com.projetopoo.mytickets.controller;

import com.projetopoo.mytickets.model.dtos.EventoDTO;
import com.projetopoo.mytickets.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

    @GetMapping
    public List<EventoDTO> listar() {
        return service.listarEventos().stream()
                .map(service::toDTO)
                .toList();
    }

    @GetMapping("/{idEvento}")
    public EventoDTO buscarPorId(@PathVariable Long idEvento) {
        return service.toDTO(service.buscarPorId(idEvento));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public EventoDTO criar(@Valid @RequestBody EventoDTO dto) {
        return service.toDTO(service.criarEvento(dto));
    }

    // gerenciamento de admins do evento (tabela event_admins)
    @PostMapping("/{idEvento}/admins/{idUsuario}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public EventoDTO adicionarAdmin(@PathVariable Long idEvento, @PathVariable Long idUsuario) {
        return service.toDTO(service.adicionarAdmin(idEvento, idUsuario));
    }

    @DeleteMapping("/{idEvento}/admins/{idUsuario}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerAdmin(@PathVariable Long idEvento, @PathVariable Long idUsuario) {
        service.removerAdmin(idEvento, idUsuario);
    }
}