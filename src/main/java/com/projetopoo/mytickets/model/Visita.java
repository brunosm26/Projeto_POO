package com.projetopoo.mytickets.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "visita")
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Long idVisita;

    // @Future fica aqui porque visita é agendamento futuro — a validação é no DTO de entrada
    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;

    @Column(name = "is_authorized")
    private Boolean isAuthorized;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id")
    @JsonIgnore
    private Usuario requester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorizer_id")
    @JsonIgnore
    private Usuario authorizer;

}
