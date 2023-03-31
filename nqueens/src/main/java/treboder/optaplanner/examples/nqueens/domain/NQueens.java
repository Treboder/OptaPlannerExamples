package treboder.optaplanner.examples.nqueens.domain;

import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.api.solver.SolverStatus;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@PlanningSolution
public class NQueens {

    @PlanningId         // OptaPlanner
    @Id @GeneratedValue // Hibernate
    private Long id;

    @ValueRangeProvider
    @ProblemFactCollectionProperty
    private List<MyRow> myRowList;

    // column is not a PlanningVariable, it is sufficient to change the row during solving,
    // because there can be only one queen per row and column
    @ProblemFactCollectionProperty
    private List<MyColumn> myColumnList;

    @PlanningEntityCollectionProperty
    private List<Queen> queenList;

    @PlanningScore
    private SimpleScore score;

    private int n; // n queens

    // Ignored by OptaPlanner, used by the UI to display solve or stop solving button
    private SolverStatus solverStatus;

    public NQueens() {
    }

    public NQueens(long id) {
        this.id = id;
    }

    public NQueens(long id, List<MyRow> myRowList, List<MyColumn> myColumnList, List<Queen> queenList) {
        this.id = id;
        this.myRowList = myRowList;
        this.myColumnList = myColumnList;
        this.queenList = queenList;
        this.n = queenList.size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MyRow> getRowList() {
        return myRowList;
    }

    public void setRowList(List<MyRow> myRowList) {
        this.myRowList = myRowList;
    }

    public List<MyColumn> getColumnList() {
        return myColumnList;
    }

    public void setColumnList(List<MyColumn> myColumnList) { this.myColumnList = myColumnList; }

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

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public SolverStatus getSolverStatus() {
        return solverStatus;
    }

    public void setSolverStatus(SolverStatus solverStatus) {
        this.solverStatus = solverStatus;
    }
}
