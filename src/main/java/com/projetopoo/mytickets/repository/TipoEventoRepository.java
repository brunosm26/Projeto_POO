package com.projetopoo.mytickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetopoo.mytickets.model.TipoEvento;

public interface TipoEventoRepository extends JpaRepository<TipoEvento, Long> {
}
