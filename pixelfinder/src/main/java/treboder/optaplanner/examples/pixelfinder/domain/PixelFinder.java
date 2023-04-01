package treboder.optaplanner.examples.pixelfinder.domain;

import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.solution.*;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@PlanningSolution
public class PixelFinder {

    @PlanningId         // OptaPlanner
    @Id @GeneratedValue // Hibernate
    Long id;

    private int mapSize;

    @ValueRangeProvider
    @ProblemFactCollectionProperty
    private List<XCoordinate> xAxis;

    @ValueRangeProvider
    @ProblemFactCollectionProperty
    private List<YCoordinate> yAxis;

    @PlanningEntityProperty
    Pixel pixel;

//    @PlanningEntityCollectionProperty
//    List<Pixel> pixelList;

    @PlanningScore
    private SimpleScore score;

    // OptaPlanner needs no-arg constructor to create a planning clone
    public PixelFinder() {
    }

    public  PixelFinder(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public List<XCoordinate> getXAxis() {
        return xAxis;
    }

    public void setXAxis(List<XCoordinate> xAxis) {
        this.xAxis = xAxis;
    }

    public List<YCoordinate> getYAxis() {
        return yAxis;
    }

    public void setYAxis(List<YCoordinate> yAxis) {
        this.yAxis = yAxis;
    }

    public Pixel getPixel() {
        return pixel;
    }

    public void setPixel(Pixel pixel) {
        this.pixel = pixel;
    }

    public SimpleScore getScore() {
        return score;
    }

    public void setScore(SimpleScore score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return this.getPixel().toString();
    }
}
