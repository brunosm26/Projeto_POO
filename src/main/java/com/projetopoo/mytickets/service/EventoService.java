package com.projetopoo.mytickets.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projetopoo.mytickets.model.Evento;
import com.projetopoo.mytickets.repository.EventoRepository;

@Service
public class EventoService {

    private final EventoRepository repository;

    public EventoService(EventoRepository repository) {
        this.repository = repository;
    }

    public List<Evento> listarEventos() {
        return repository.findAll();
    }

    public Evento buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Evento não encontrado com id: " + id));
    }

    public Evento criarEvento(Evento evento) {
        return repository.save(evento);
    }
}
