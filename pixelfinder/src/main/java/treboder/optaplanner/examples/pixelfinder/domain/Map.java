package treboder.optaplanner.examples.pixelfinder.domain;

import org.optaplanner.core.api.domain.lookup.PlanningId;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// Problem fact
public class Map {

    @PlanningId         // OptaPlanner
    @Id @GeneratedValue // Hibernate
    Long id;

    // 2D array
    int[][] map;

}
