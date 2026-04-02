package com.projetopoo.mytickets.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.projetopoo.mytickets.model.Visita;
import com.projetopoo.mytickets.repository.VisitaRepository;

@Service
public class VisitaService {

    private final VisitaRepository repository;

    public VisitaService(VisitaRepository repository) {
        this.repository = repository;
    }

    public Visita criarVisita(Visita visita) {
        return repository.save(visita);
    }

    public List<Visita> listarVisitas() {
        return repository.findAll();
    }

    public Visita buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Visita não encontrada com id: " + id));
    }
}
