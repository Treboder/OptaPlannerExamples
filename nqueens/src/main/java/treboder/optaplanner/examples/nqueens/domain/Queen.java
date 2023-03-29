package treboder.optaplanner.examples.nqueens.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import treboder.optaplanner.examples.nqueens.solver.QueenDifficultyWeightFactory;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@PlanningEntity(difficultyWeightFactoryClass = QueenDifficultyWeightFactory.class)
//@PlanningEntity
public class Queen {

    @PlanningId
    @Id
    @GeneratedValue
    private Long id;

    private Column column;

    // Planning variables: changes during planning, between score calculations.
    private Row row;

    public Queen() {
    }

    public Queen(long id) {
        this.id = id;
    }

    public Queen(long id, Row row, Column column) {
        this.id = id;
        this.row = row;
        this.column = column;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

   //@PlanningVariable(strengthWeightFactoryClass = RowStrengthWeightFactory.class)
    @PlanningVariable
    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    @JsonIgnore
    public int getColumnIndex() {
        return column.getIndex();
    }

    @JsonIgnore
    public int getRowIndex() {
        if (row == null) {
            return Integer.MIN_VALUE;
        }
        return row.getIndex();
    }

    @JsonIgnore
    public int getAscendingDiagonalIndex() {
        return (getColumnIndex() + getRowIndex());
    }

    @JsonIgnore
    public int getDescendingDiagonalIndex() {
        return (getColumnIndex() - getRowIndex());
    }

    @Override
    public String toString() {
        return "Queen-" + column.getIndex();
    }

}

