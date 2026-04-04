package com.projetopoo.mytickets.model;

import com.projetopoo.mytickets.enums.AggregationType;
import com.projetopoo.mytickets.enums.Dimension;
import java.util.List;

public class Estatistica {

    private Evento event;
    private List<Dimension> dimensionColumns;
    private List<AggregationType> metricColumns;
}