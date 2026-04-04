package com.projetopoo.mytickets.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetopoo.mytickets.model.enums.EventCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

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

    public Evento() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getIdEvento() { return idEvento; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public EventCategory getCategory() { return category; }
    public void setCategory(EventCategory category) { this.category = category; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getLocationDetail() { return locationDetail; }
    public void setLocationDetail(String locationDetail) { this.locationDetail = locationDetail; }

    public List<Usuario> getAdmins() { return admins; }
    public void setAdmins(List<Usuario> admins) { this.admins = admins; }

    public Usuario getCreator() { return creator; }
    public void setCreator(Usuario creator) { this.creator = creator; }

    public Boolean getFree() { return isFree; }
    public void setFree(Boolean free) { isFree = free; }

    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }

    public TipoEvento getEventType() { return eventType; }
    public void setEventType(TipoEvento eventType) { this.eventType = eventType; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getExpectedAttendance() { return expectedAttendance; }
    public void setExpectedAttendance(int expectedAttendance) { this.expectedAttendance = expectedAttendance; }

    public Boolean getWeekend() { return isWeekend; }
    public void setWeekend(Boolean weekend) { isWeekend = weekend; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
