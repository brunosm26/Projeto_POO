package com.projetopoo.mytickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.projetopoo.mytickets.model.Evento;
import com.projetopoo.mytickets.model.enums.EventCategory;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByCategory(EventCategory category);
}
