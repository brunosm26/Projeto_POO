package com.projetopoo.mytickets.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_evento")
public class TipoEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_type_id")
    private Long idTipoEvento;

    @Column(name = "name")
    private String name;

    @Column(name = "subtype")
    private String subtype;

    public Long getIdTipoEvento() {
        return idTipoEvento;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
}