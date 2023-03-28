package treboder.optaplanner.examples.timetable.timetable.persistence;


import org.springframework.data.repository.PagingAndSortingRepository;
import treboder.optaplanner.examples.timetable.timetable.domain.Lesson;

import java.util.List;

public interface LessonRepository extends PagingAndSortingRepository<Lesson, Long> {

    @Override
    List<Lesson> findAll();

}
