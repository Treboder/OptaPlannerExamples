package com.mosaic.puzzle.domain;

import org.optaplanner.core.api.domain.lookup.PlanningId;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class PuzzleYCoordinate {

    @PlanningId         // OptaPlanner
    @Id @GeneratedValue // Hibernate
    private Long id;

    private int value;

    public PuzzleYCoordinate(int y) {
        this.id = (long) -y;
        this.value = y;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Y-" + value;
    }


}
