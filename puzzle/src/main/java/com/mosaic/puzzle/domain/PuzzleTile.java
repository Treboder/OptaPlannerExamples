package com.mosaic.puzzle.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@PlanningEntity
public class PuzzleTile {

    @PlanningId         // OptaPlanner
    @Id @GeneratedValue // Hibernate
    Long id;

    int value;

    @PlanningVariable
    PuzzleXCoordinate x;

    @PlanningVariable
    PuzzleYCoordinate y;

    // OptaPlanner needs no-arg constructor to create a planning clone
    public PuzzleTile() {
    }

    public PuzzleTile(Long id, int value) {
        this.id = id;
        this.value = value;
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

    public PuzzleXCoordinate getX() {
        return x;
    }

    public void setX(PuzzleXCoordinate x) {
        this.x = x;
    }

    public PuzzleYCoordinate getY() {
        return y;
    }

    public void setY(PuzzleYCoordinate y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Tile[" + getXValue() + "," + getYValue() + "]";
    }

    @JsonIgnore
    public int getXValue() {
        if (this.x == null) {
            return Integer.MIN_VALUE;
        }
        return this.x.getValue();
    }

    @JsonIgnore
    public int getYValue() {
        if (this.y == null) {
            return Integer.MIN_VALUE;
        }
        return this.y.getValue();
    }

    @JsonIgnore
    public boolean isInitialized() {
        return this.x != null && this.y != null;
    }

    @JsonIgnore
    public boolean interferesWith(PuzzleTile other) {
        // false of one is not initialized
        if(!this.isInitialized() | !other.isInitialized())
            return false;
        // true if both occupy the same spot
        return this.getXValue() == other.getXValue() && this.getYValue() == other.getYValue();
    }
}
