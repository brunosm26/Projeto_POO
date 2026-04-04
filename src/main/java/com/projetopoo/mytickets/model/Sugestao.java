package com.projetopoo.mytickets.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetopoo.mytickets.model.enums.EventCategory;
import com.projetopoo.mytickets.model.enums.SuggestionStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "sugestao")
public class Sugestao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "suggestion_id")
    private Long idSugestao;

    @Size(min = 5, max = 50)
    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "description", length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private EventCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SuggestionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    @JsonIgnore
    private Usuario creator;

    public Sugestao() {}

    @PrePersist
    protected void onCreate() {
        if (this.status == null) {
            this.status = SuggestionStatus.PENDENTE;
        }
    }

    public Long getIdSugestao() { return idSugestao; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public EventCategory getCategory() { return category; }
    public void setCategory(EventCategory category) { this.category = category; }

    public SuggestionStatus getStatus() { return status; }
    public void setStatus(SuggestionStatus status) { this.status = status; }

    public Usuario getCreator() { return creator; }
    public void setCreator(Usuario creator) { this.creator = creator; }
}
