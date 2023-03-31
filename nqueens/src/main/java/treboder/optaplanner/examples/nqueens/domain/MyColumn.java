package treboder.optaplanner.examples.nqueens.domain;

import org.optaplanner.core.api.domain.lookup.PlanningId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // Hibernate
public class MyColumn {

    @PlanningId         // OptaPlanner
    @Id @GeneratedValue // Hibernate
    private Long id;

    private int index;

    public MyColumn() {
    }

    public MyColumn(int index) {
        this.index = index;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Column-" + index;
    }

}
