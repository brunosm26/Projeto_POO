package com.projetopoo.mytickets.controller;

import com.projetopoo.mytickets.model.dtos.VisitaDTO;
import com.projetopoo.mytickets.model.dtos.VisitaResponseDTO;
import com.projetopoo.mytickets.service.VisitaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitas")
public class VisitaController {

    private final VisitaService service;

    public VisitaController(VisitaService service) {
        this.service = service;
    }

    @GetMapping
    public List<VisitaResponseDTO> listar() {
        return service.listarVisitas().stream()
                .map(service::toResponseDTO)
                .toList();
    }

    @GetMapping("/{idVisita}")
    public VisitaResponseDTO buscarPorId(@PathVariable Long idVisita) {
        return service.toResponseDTO(service.buscarPorId(idVisita));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VisitaResponseDTO criar(@Valid @RequestBody VisitaDTO dto) {
        return service.toResponseDTO(service.criarVisita(dto));
    }
}
