package treboder.optaplanner.examples.nqueens.domain;

import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@PlanningSolution
public class NQueens {

    private int n; // n queens

    @PlanningId
    @Id
    @GeneratedValue
    private Long id;

    @ValueRangeProvider
    @ProblemFactCollectionProperty
    private List<Row> rowList;

    // column is not a PlanningVariable, it is sufficient to change the row during solving,
    // because there can be only one queen per row and column
    @ProblemFactCollectionProperty
    private List<Column> columnList;

    @PlanningEntityCollectionProperty
    private List<Queen> queenList;

    @PlanningScore
    private SimpleScore score;

    public NQueens() {
    }

    public NQueens(long id) {
        this.id = id;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public List<Row> getRowList() {
        return rowList;
    }

    public void setRowList(List<Row> rowList) {
        this.rowList = rowList;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) { this.columnList = columnList; }

    public List<Queen> getQueenList() {
        return queenList;
    }

    public void setQueenList(List<Queen> queenList) { this.queenList = queenList; }

    public SimpleScore getScore() {
        return score;
    }

    public void setScore(SimpleScore score) {
        this.score = score;
    }



}
