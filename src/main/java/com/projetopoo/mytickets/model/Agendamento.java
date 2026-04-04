package com.projetopoo.mytickets.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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

    @PrePersist
    protected void onCreate() {
        if (this.bookedAt == null) {
            this.bookedAt = LocalDateTime.now();
        }
    }

}
