package com.projetopoo.mytickets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetopoo.mytickets.model.Inscricao;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    List<Inscricao> findByEventoId(Long eventoId);

    List<Inscricao> findByUsuarioId(Long usuarioId);

}
