package treboder.optaplanner.examples.timetable;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import treboder.optaplanner.examples.timetable.controller.TimeTableController;
import treboder.optaplanner.examples.timetable.data.TimeTableDemoData;
import treboder.optaplanner.examples.timetable.domain.Lesson;
import treboder.optaplanner.examples.timetable.domain.Room;
import treboder.optaplanner.examples.timetable.domain.TimeTable;
import treboder.optaplanner.examples.timetable.domain.Timeslot;
import treboder.optaplanner.examples.timetable.persistence.LessonRepository;
import treboder.optaplanner.examples.timetable.persistence.RoomRepository;
import treboder.optaplanner.examples.timetable.persistence.TimeTableRepository;
import treboder.optaplanner.examples.timetable.persistence.TimeslotRepository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = {
        // Effectively disable spent-time termination in favor of the best-score-limit
        "optaplanner.solver.termination.spent-limit=1h",
        "optaplanner.solver.termination.best-score-limit=0hard/*soft"})
public class TimeTableControllerTest {

    @Autowired
    private TimeTableController timeTableController;

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private TimeslotRepository timeslotRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private LessonRepository lessonRepository;

    @Test
    @Timeout(600_000)
    public void solve() {
        TimeTable problem = generateProblem();
        TimeTable solution = timeTableController.solve(problem);
        assertFalse(solution.getLessonList().isEmpty());
        for (Lesson lesson : solution.getLessonList()) {
            assertNotNull(lesson.getTimeslot());
            assertNotNull(lesson.getRoom());
        }
        assertTrue(solution.getScore().isFeasible());
    }

    private TimeTable generateProblem() {
        timeslotRepository.save(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
        timeslotRepository.save(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
        timeslotRepository.save(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
        timeslotRepository.save(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
        timeslotRepository.save(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));
        timeslotRepository.save(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
        timeslotRepository.save(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
        timeslotRepository.save(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
        timeslotRepository.save(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
        timeslotRepository.save(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));

        roomRepository.save(new Room("Room A"));
        roomRepository.save(new Room("Room B"));
        roomRepository.save(new Room("Room C"));

        lessonRepository.save(new Lesson("Math", "A. Turing", "9th grade"));
        lessonRepository.save(new Lesson("Math", "A. Turing", "9th grade"));
        lessonRepository.save(new Lesson("Physics", "M. Curie", "9th grade"));
        lessonRepository.save(new Lesson("Chemistry", "M. Curie", "9th grade"));
        lessonRepository.save(new Lesson("Biology", "C. Darwin", "9th grade"));
        lessonRepository.save(new Lesson("History", "I. Jones", "9th grade"));
        lessonRepository.save(new Lesson("English", "I. Jones", "9th grade"));
        lessonRepository.save(new Lesson("English", "I. Jones", "9th grade"));
        lessonRepository.save(new Lesson("Spanish", "P. Cruz", "9th grade"));
        lessonRepository.save(new Lesson("Spanish", "P. Cruz", "9th grade"));

        lessonRepository.save(new Lesson("Math", "A. Turing", "10th grade"));
        lessonRepository.save(new Lesson("Math", "A. Turing", "10th grade"));
        lessonRepository.save(new Lesson("Math", "A. Turing", "10th grade"));
        lessonRepository.save(new Lesson("Physics", "M. Curie", "10th grade"));
        lessonRepository.save(new Lesson("Chemistry", "M. Curie", "10th grade"));
        lessonRepository.save(new Lesson("French", "M. Curie", "10th grade"));
        lessonRepository.save(new Lesson("Geography", "C. Darwin", "10th grade"));
        lessonRepository.save(new Lesson("History", "I. Jones", "10th grade"));
        lessonRepository.save(new Lesson("English", "P. Cruz", "10th grade"));
        lessonRepository.save(new Lesson("Spanish", "P. Cruz", "10th grade"));

        Lesson lesson = lessonRepository.findAll(Sort.by("id")).iterator().next();
        lesson.setTimeslot(timeslotRepository.findAll(Sort.by("id")).iterator().next());
        lesson.setRoom(roomRepository.findAll(Sort.by("id")).iterator().next());

        lessonRepository.save(lesson);

        TimeTable problem = new TimeTable(timeslotRepository.findAll(), roomRepository.findAll(), lessonRepository.findAll());
        timeTableRepository.save(problem);
        return problem;
    }

}
