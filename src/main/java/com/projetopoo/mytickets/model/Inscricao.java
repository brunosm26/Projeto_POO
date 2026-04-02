package com.projetopoo.mytickets.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "inscricao")
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @Future
    @Column(name = "dataHoraInscricao", nullable = false)
    private LocalDateTime dataHoraInscricao;

    @Positive
    @Column(name = "quantidadeVisitantes", nullable = false)
    private int quantidadeVisitantes;

    public Inscricao() {
    }

    public Long getId() { return id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }

    public LocalDateTime getDataHoraInscricao() { return dataHoraInscricao; }
    public void setDataHoraInscricao(LocalDateTime dataHoraInscricao) { this.dataHoraInscricao = dataHoraInscricao; }

    public int getQuantidadeVisitantes() { return quantidadeVisitantes; }
    public void setQuantidadeVisitantes(int quantidadeVisitantes) { this.quantidadeVisitantes = quantidadeVisitantes; }

}
