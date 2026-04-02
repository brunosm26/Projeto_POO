package com.projetopoo.mytickets.model;

import com.projetopoo.mytickets.model.enums.EventCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 50)
    @Column(name = "nomeEvento", nullable = false)
    private String nomeEvento;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @Positive
    @Column(name = "capacidade")
    private int capacidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private EventCategory category;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "location_detail")
    private String locationDetail;

    @ManyToMany
    @JoinTable(name = "evento_admin",
        joinColumns = @JoinColumn(name = "evento_id"),
        inverseJoinColumns = @JoinColumn(name = "admin_id"))
    private List<Usuario> admins;

    @ManyToOne
    @JoinColumn(name = "criador_id")
    private Usuario criador;

    @Column(name = "gratuito")
    private Boolean gratuito;

    @FutureOrPresent
    @Column(name = "dataHora")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "tipo_evento_id")
    private TipoEvento tipo;

    @PositiveOrZero
    @Column(name = "preco")
    private double preco;

    @Positive
    @Column(name = "expectedPublic")
    private int expectedPublic;

    @Column(name = "isFimDeSemana")
    private Boolean isFimDeSemana;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Evento() {
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public String getNomeEvento() { return nomeEvento; }
    public void setNomeEvento(String nomeEvento) { this.nomeEvento = nomeEvento; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getCapacidade() { return capacidade; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }

    public EventCategory getCategory() { return category; }
    public void setCategory(EventCategory category) { this.category = category; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getLocationDetail() { return locationDetail; }
    public void setLocationDetail(String locationDetail) { this.locationDetail = locationDetail; }

    public List<Usuario> getAdmins() { return admins; }
    public void setAdmins(List<Usuario> admins) { this.admins = admins; }

    public Usuario getCriador() { return criador; }
    public void setCriador(Usuario criador) { this.criador = criador; }

    public Boolean getGratuito() { return gratuito; }
    public void setGratuito(Boolean gratuito) { this.gratuito = gratuito; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public TipoEvento getTipo() { return tipo; }
    public void setTipo(TipoEvento tipo) { this.tipo = tipo; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public int getExpectedPublic() { return expectedPublic; }
    public void setExpectedPublic(int expectedPublic) { this.expectedPublic = expectedPublic; }

    public Boolean getIsFimDeSemana() { return isFimDeSemana; }
    public void setIsFimDeSemana(Boolean isFimDeSemana) { this.isFimDeSemana = isFimDeSemana; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
