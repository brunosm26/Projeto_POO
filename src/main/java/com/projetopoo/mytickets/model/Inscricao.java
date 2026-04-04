package com.projetopoo.mytickets.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Table(name = "inscricao")
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inscription_id")
    private Long idInscricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Evento event;

    // @Future removido — a data de inscrição pode ser o momento atual (LocalDateTime.now())
    @Column(name = "registration_at", nullable = false)
    private LocalDateTime registrationAt;

    @Positive
    @Column(name = "visitor_count", nullable = false)
    private int visitorCount;

    public Inscricao() {}

    public Long getIdInscricao() { return idInscricao; }

    public Usuario getUser() { return user; }
    public void setUser(Usuario user) { this.user = user; }

    public Evento getEvent() { return event; }
    public void setEvent(Evento event) { this.event = event; }

    public LocalDateTime getRegistrationAt() { return registrationAt; }
    public void setRegistrationAt(LocalDateTime registrationAt) { this.registrationAt = registrationAt; }

    public int getVisitorCount() { return visitorCount; }
    public void setVisitorCount(int visitorCount) { this.visitorCount = visitorCount; }
}
