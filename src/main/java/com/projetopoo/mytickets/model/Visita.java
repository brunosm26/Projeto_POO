package com.projetopoo.mytickets.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.persistence.Column;


@Entity
@Table(name = "visita")
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Future
    @Column(name = "dataHora")
    private LocalDateTime dataHora;

    @Column(name = "autorizada")
    private Boolean isAutorizada;

    @ManyToOne
    @JoinColumn(name = "solicitante_id")
    private Usuario usuarioSolicitante;

    @ManyToOne
    @JoinColumn(name = "autorizador_id")
    private Usuario adminAutorizador;

    public Long getId() { return this.id; }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Usuario getUsuarioSolicitante() {
        return usuarioSolicitante;
    }
    public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }

    public Boolean getIsAutorizada() {
        return isAutorizada;
    }
    public void setIsAutorizada(Boolean isAutorizada) {
        this.isAutorizada = isAutorizada;
    }

    public Usuario getAdminAutorizador() {
        return adminAutorizador;
    }
    public void setAdminAutorizador(Usuario adminAutorizador) {
        this.adminAutorizador = adminAutorizador;
    }

}
