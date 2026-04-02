package com.projetopoo.mytickets.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projetopoo.mytickets.model.Inscricao;
import com.projetopoo.mytickets.service.InscricaoService;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {

    private final InscricaoService service;

    public InscricaoController(InscricaoService service) {
        this.service = service;
    }

    // POST /inscricoes?usuarioId=1&eventoId=2 — inscreve um usuário em um evento
    // A lógica de lotação (capacidade) já é verificada no InscricaoService
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Corrigido: retorna 201 Created em vez de 200
    public Inscricao inscrever(
            @RequestParam Long usuarioId,
            @RequestParam Long eventoId
    ) {
        return service.inscrever(usuarioId, eventoId);
    }

    // GET /inscricoes/usuario/{id} — lista todas as inscrições de um usuário
    @GetMapping("/usuario/{id}")
    public List<Inscricao> listar(@PathVariable Long id) {
        return service.listarPorUsuario(id);
    }
}
