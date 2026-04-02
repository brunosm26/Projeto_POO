package com.projetopoo.mytickets.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projetopoo.mytickets.model.Visita;
import com.projetopoo.mytickets.service.VisitaService;

@RestController
@RequestMapping("/api/visitas")
public class VisitaController {

    private final VisitaService service;

    public VisitaController(VisitaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Visita>> listar() {
        return ResponseEntity.ok(service.listarVisitas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visita> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Visita> criar(@RequestBody Visita visita) {
        return ResponseEntity.ok(service.criarVisita(visita));
    }
}
