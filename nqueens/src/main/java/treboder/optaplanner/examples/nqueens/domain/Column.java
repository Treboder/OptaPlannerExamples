package treboder.optaplanner.examples.nqueens.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@JsonIdentityInfo(scope = Column.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Column {

    @PlanningId
    @Id
    @GeneratedValue
    private Long id;

    private int index;

    public Column() {
    }

    public Column(int index) {
        this.id = Long.valueOf(index);
        this.index = index;
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
