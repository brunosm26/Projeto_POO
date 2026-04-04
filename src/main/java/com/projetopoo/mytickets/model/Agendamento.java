package com.projetopoo.mytickets.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long idAgendamento;

    @Column(name = "booked_at")
    private LocalDateTime bookedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Evento event;

    @Column(name = "person_count")
    private int personCount;

    public Agendamento() {}

    @PrePersist
    protected void onCreate() {
        if (this.bookedAt == null) {
            this.bookedAt = LocalDateTime.now();
        }
    }

    public Long getIdAgendamento() { return idAgendamento; }

    public LocalDateTime getBookedAt() { return bookedAt; }
    public void setBookedAt(LocalDateTime bookedAt) { this.bookedAt = bookedAt; }

    public Usuario getUser() { return user; }
    public void setUser(Usuario user) { this.user = user; }

    public Evento getEvent() { return event; }
    public void setEvent(Evento event) { this.event = event; }

    public int getPersonCount() { return personCount; }
    public void setPersonCount(int personCount) { this.personCount = personCount; }
}
