package treboder.optaplanner.examples.nqueens.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import treboder.optaplanner.examples.nqueens.solver.QueenDifficultyWeightFactory;
import treboder.optaplanner.examples.nqueens.solver.RowStrengthWeightFactory;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@PlanningEntity changes during planning, QueenDifficultyWeightFactory is optional
@PlanningEntity(difficultyWeightFactoryClass = QueenDifficultyWeightFactory.class)
public class Queen {

    @PlanningId
    @Id
    @GeneratedValue
    private Long id;

    //@PlanningVariable changes during planning, RowStrengthWeightFactory is optional
    @PlanningVariable(strengthWeightFactoryClass = RowStrengthWeightFactory.class)
    private Row row;

    // column is not a PlanningVariable, it is sufficient to change the row during solving,
    // because there can be only one queen per row and column
    private Column column;

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

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Queen-" + column.getIndex();
    }

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


}

