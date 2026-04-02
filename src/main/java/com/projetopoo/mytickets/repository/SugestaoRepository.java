package com.projetopoo.mytickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetopoo.mytickets.model.Sugestao;

public interface SugestaoRepository extends JpaRepository<Sugestao, Long> {
}
