package com.projetopoo.mytickets.model;

import com.projetopoo.mytickets.enums.AggregationType;
import com.projetopoo.mytickets.enums.Dimension;
import java.util.List;

public class Estatistica {

    private Evento event;
    private List<Dimension> dimensionColumns;
    private List<AggregationType> metricColumns;

    public Estatistica() {
    }

    public Estatistica(Evento event, List<Dimension> dimensionColumns, List<AggregationType> metricColumns) {
        this.event = event;
        this.dimensionColumns = dimensionColumns;
        this.metricColumns = metricColumns;
    }

    public Evento getEvent() {
        return event;
    }

    public void setEvent(Evento event) {
        this.event = event;
    }

    public List<Dimension> getDimensionColumns() {
        return dimensionColumns;
    }

    public void setDimensionColumns(List<Dimension> dimensionColumns) {
        this.dimensionColumns = dimensionColumns;
    }

    public List<AggregationType> getMetricColumns() {
        return metricColumns;
    }

    public void setMetricColumns(List<AggregationType> metricColumns) {
        this.metricColumns = metricColumns;
    }
}