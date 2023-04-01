package treboder.optaplanner.examples.pixelfinder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.springframework.data.geo.Point;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@PlanningEntity
public class Pixel {

    @PlanningId         // OptaPlanner
    @Id @GeneratedValue // Hibernate
    Long id;

    int value;

    @PlanningVariable
    XCoordinate x;

    @PlanningVariable
    YCoordinate y;

    // OptaPlanner needs no-arg constructor to create a planning clone
    public Pixel() {
    }

    public Pixel(Long id) {
        this.id = id;
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

    public XCoordinate getX() {
        return x;
    }

    public void setX(XCoordinate x) {
        this.x = x;
    }

    public YCoordinate getY() {
        return y;
    }

    public void setY(YCoordinate y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Pixel[" + getXValue() + "," + getYValue() + "]";
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
}
