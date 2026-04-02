package com.projetopoo.mytickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetopoo.mytickets.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
