package com.projetopoo.mytickets.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.projetopoo.mytickets.model.Agendamento;
import com.projetopoo.mytickets.repository.AgendamentoRepository;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;

    public AgendamentoService(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public Agendamento criarAgendamento(Agendamento agendamento) {
        return repository.save(agendamento);
    }

    public List<Agendamento> listarAgendamentos() {
        return repository.findAll();
    }

    public Agendamento buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento não encontrado com id: " + id));
    }
}
