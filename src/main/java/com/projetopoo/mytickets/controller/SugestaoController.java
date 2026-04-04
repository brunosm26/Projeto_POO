package com.projetopoo.mytickets.controller;

import com.projetopoo.mytickets.model.dtos.SugestaoDTO;
import com.projetopoo.mytickets.model.dtos.SugestaoResponseDTO;
import com.projetopoo.mytickets.service.SugestaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sugestoes")
public class SugestaoController {

    private final SugestaoService service;

    public SugestaoController(SugestaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<SugestaoResponseDTO> listar() {
        return service.listarSugestoes().stream()
                .map(service::toResponseDTO)
                .toList();
    }

    @GetMapping("/{idSugestao}")
    public SugestaoResponseDTO buscarPorId(@PathVariable Long idSugestao) {
        return service.toResponseDTO(service.buscarPorId(idSugestao));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SugestaoResponseDTO criar(@Valid @RequestBody SugestaoDTO dto) {
        return service.toResponseDTO(service.criarSugestao(dto));
    }
}
