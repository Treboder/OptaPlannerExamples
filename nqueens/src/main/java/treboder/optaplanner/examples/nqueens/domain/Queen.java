package treboder.optaplanner.examples.nqueens.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import treboder.optaplanner.examples.nqueens.solver.QueenDifficultyWeightFactory;
import treboder.optaplanner.examples.nqueens.solver.RowStrengthWeightFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

//@PlanningEntity changes during planning, optionally QueenDifficultyWeightFactory improves solver performance
// cf. https://docs.jboss.org/optaplanner/release/7.54.0.Final/optaplanner-docs/html_single/#plannerConfiguration
@PlanningEntity(difficultyWeightFactoryClass = QueenDifficultyWeightFactory.class)
@Entity // Hibernate
public class Queen {

    @PlanningId         // OptaPlanner
    @Id @GeneratedValue // Hibernate
    private Long id;

    //@PlanningVariable changes during planning, optionally RowStrengthWeightFactory improves solver performance
    // cf. https://docs.jboss.org/optaplanner/release/7.54.0.Final/optaplanner-docs/html_single/#plannerConfiguration
    @PlanningVariable(strengthWeightFactoryClass = RowStrengthWeightFactory.class)
    @OneToOne
    private MyRow myRow;

    // column is not a PlanningVariable, it is sufficient to change the row during solving,
    // because there can be only one queen per row and column
    @OneToOne
    private MyColumn myColumn;

    public Queen() {
    }

    public Queen(long id) {
        this.id = id;
    }

    public Queen(long id, MyRow myRow, MyColumn myColumn) {
        this.id = id;
        this.myRow = myRow;
        this.myColumn = myColumn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MyRow getRow() {
        return myRow;
    }

    public void setRow(MyRow myRow) {
        this.myRow = myRow;
    }

    public MyColumn getColumn() {
        return myColumn;
    }

    public void setColumn(MyColumn myColumn) {
        this.myColumn = myColumn;
    }

    @Override
    public String toString() {
        return "Queen-" + myColumn.getIndex();
    }

    @JsonIgnore
    public int getColumnIndex() {
        return myColumn.getIndex();
    }

    @JsonIgnore
    public int getRowIndex() {
        if (myRow == null) {
            return Integer.MIN_VALUE;
        }
        return myRow.getIndex();
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

