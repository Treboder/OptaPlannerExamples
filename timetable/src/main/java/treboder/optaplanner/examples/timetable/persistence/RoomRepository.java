package treboder.optaplanner.examples.timetable.persistence;


import org.springframework.data.repository.PagingAndSortingRepository;
import treboder.optaplanner.examples.timetable.domain.Room;

import java.util.List;

public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {

    @Override
    List<Room> findAll();

}
