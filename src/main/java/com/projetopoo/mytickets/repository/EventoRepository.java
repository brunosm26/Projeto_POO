package com.projetopoo.mytickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetopoo.mytickets.model.Evento;

public interface EventoRepository extends JpaRepository <Evento, Long>  {

}
