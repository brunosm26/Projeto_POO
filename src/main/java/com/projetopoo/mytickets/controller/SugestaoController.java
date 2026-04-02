package com.projetopoo.mytickets.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projetopoo.mytickets.model.Sugestao;
import com.projetopoo.mytickets.service.SugestaoService;

@RestController
@RequestMapping("/api/sugestoes")
public class SugestaoController {

    private final SugestaoService service;

    public SugestaoController(SugestaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Sugestao>> listar() {
        return ResponseEntity.ok(service.listarSugestoes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sugestao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Sugestao> criar(@RequestBody Sugestao sugestao) {
        return ResponseEntity.ok(service.criarSugestao(sugestao));
    }
}
