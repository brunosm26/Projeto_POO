package com.projetopoo.mytickets.model;

import com.projetopoo.mytickets.enums.AggregationType;
import com.projetopoo.mytickets.enums.Dimension;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Estatistica {

    private Evento event;
    private List<Dimension> dimensionColumns;
    private List<AggregationType> metricColumns;
}