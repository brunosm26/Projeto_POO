package com.projetopoo.mytickets.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetopoo.mytickets.model.enums.EventCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long idEvento;

    @Size(min = 5, max = 50)
    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "description", length = 500)
    private String description;

    @Positive
    @Column(name = "capacity")
    private int capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private EventCategory category;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "location_detail")
    private String locationDetail;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "event_admins",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Usuario> admins;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    @JsonIgnore
    private Usuario creator;

    @Column(name = "is_free")
    private Boolean isFree;

    @FutureOrPresent
    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_type_id")
    @JsonIgnore
    private TipoEvento eventType;

    @PositiveOrZero
    @Column(name = "price")
    private double price;

    @Positive
    @Column(name = "expected_attendance")
    private int expectedAttendance;

    @Column(name = "is_weekend")
    private Boolean isWeekend;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
