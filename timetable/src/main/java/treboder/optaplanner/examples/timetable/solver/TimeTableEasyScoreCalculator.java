//package treboder.optaplanner.examples.timetable.solver;
//
//import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
//import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;
//import treboder.optaplanner.examples.timetable.domain.Lesson;
//import treboder.optaplanner.examples.timetable.domain.TimeTable;
//
//import java.util.List;
//
//// Unfortunately that does not scale well, because it is non-incremental:
//// every time a lesson is assigned to a different time slot or room, all lessons are re-evaluated to calculate the new score.
//
//public class TimeTableEasyScoreCalculator implements EasyScoreCalculator<TimeTable, HardSoftScore> {
//
//    @Override
//    public HardSoftScore calculateScore(TimeTable timeTable) {
//        List<Lesson> lessonList = timeTable.getLessonList();
//        int hardScore = 0;
//        for (Lesson a : lessonList) {
//            for (Lesson b : lessonList) {
//                if (a.getTimeslot() != null && a.getTimeslot().equals(b.getTimeslot())
//                        && a.getId() < b.getId()) {
//                    // A room can accommodate at most one lesson at the same time.
//                    if (a.getRoom() != null && a.getRoom().equals(b.getRoom())) {
//                        hardScore--;
//                    }
//                    // A teacher can teach at most one lesson at the same time.
//                    if (a.getTeacher().equals(b.getTeacher())) {
//                        hardScore--;
//                    }
//                    // A student can attend at most one lesson at the same time.
//                    if (a.getStudentGroup().equals(b.getStudentGroup())) {
//                        hardScore--;
//                    }
//                }
//            }
//        }
//        int softScore = 0;
//        // Soft constraints are only implemented in the optaplanner-quickstarts code
//        return HardSoftScore.of(hardScore, softScore);
//    }
//
//}