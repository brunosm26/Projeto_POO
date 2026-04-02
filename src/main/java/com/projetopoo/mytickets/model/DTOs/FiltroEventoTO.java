package com.projetopoo.mytickets.model.DTOs;

import com.projetopoo.mytickets.model.TipoEvento;
import java.time.LocalDateTime;

public class FiltroEventoTO {
    
    private Boolean isPaid;
    private LocalDateTime isBefore;
    private LocalDateTime isAfter;
    private TipoEvento tipo;
    
    public Boolean getIsPaid() {
        return isPaid;
    }
    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }
    public LocalDateTime getIsBefore() {
        return isBefore;
    }
    public void setIsBefore(LocalDateTime isBefore) {
        this.isBefore = isBefore;
    }
    public LocalDateTime getIsAfter() {
        return isAfter;
    }
    public void setIsAfter(LocalDateTime isAfter) {
        this.isAfter = isAfter;
    }
    public TipoEvento getTipo() {
        return tipo;
    }
    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }
    
}
