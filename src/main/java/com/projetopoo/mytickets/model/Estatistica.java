package com.projetopoo.mytickets.model;

import com.projetopoo.mytickets.enums.TipoAgregacao;

import java.util.List;
import com.projetopoo.mytickets.enums.Dimensao;

public class Estatistica {
    public Estatistica() {}

    private Evento evento;
    private List<Dimensao> colunasDimensao;
    private List<TipoAgregacao> colunasMetrica;

    public Evento getEvento() {
        return evento;
    }
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    public List<Dimensao> getColunasDimensao() {
        return colunasDimensao;
    }
    public void setColunasDimensao(List<Dimensao> colunasDimensao) {
        this.colunasDimensao = colunasDimensao;
    }
    public List<TipoAgregacao> getColunasMetrica() {
        return colunasMetrica;
    }
    public void setColunasMetrica(List<TipoAgregacao> colunasMetrica) {
        this.colunasMetrica = colunasMetrica;
    }
}
