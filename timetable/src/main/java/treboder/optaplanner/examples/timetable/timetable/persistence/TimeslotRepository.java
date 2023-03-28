package treboder.optaplanner.examples.timetable.timetable.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import treboder.optaplanner.examples.timetable.timetable.domain.Timeslot;

import java.util.List;

public interface TimeslotRepository extends PagingAndSortingRepository<Timeslot, Long> {

    @Override
    List<Timeslot> findAll();

}
