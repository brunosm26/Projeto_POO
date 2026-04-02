package com.projetopoo.mytickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetopoo.mytickets.model.Visita;

public interface VisitaRepository extends JpaRepository<Visita, Long> {
}
