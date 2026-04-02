package com.projetopoo.mytickets.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.projetopoo.mytickets.model.Sugestao;
import com.projetopoo.mytickets.repository.SugestaoRepository;

@Service
public class SugestaoService {

    private final SugestaoRepository repository;

    public SugestaoService(SugestaoRepository repository) {
        this.repository = repository;
    }

    public Sugestao criarSugestao(Sugestao sugestao) {
        return repository.save(sugestao);
    }

    public List<Sugestao> listarSugestoes() {
        return repository.findAll();
    }

    public Sugestao buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Sugestão não encontrada com id: " + id));
    }
}
