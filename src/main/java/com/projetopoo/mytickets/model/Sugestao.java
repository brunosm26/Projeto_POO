package com.projetopoo.mytickets.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "sugestao")
public class Sugestao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 50)
    @Column(name = "nomeEvento", nullable = false)
    private String nomeEvento;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "criador_id")
    private Usuario criador;

    @FutureOrPresent
    @Column(name = "dataHora")
    private LocalDateTime dataHora;
}
