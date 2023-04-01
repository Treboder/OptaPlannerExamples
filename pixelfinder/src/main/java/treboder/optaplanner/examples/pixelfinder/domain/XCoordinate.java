package treboder.optaplanner.examples.pixelfinder.domain;

import org.optaplanner.core.api.domain.lookup.PlanningId;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class XCoordinate {

    @PlanningId         // OptaPlanner
    @Id @GeneratedValue // Hibernate
    private Long id;

    private int value;

    public XCoordinate(int x) {
        this.id = Long.valueOf(x);
        this.value = x;
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
        return "X-" + value;
    }

}
