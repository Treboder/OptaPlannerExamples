package treboder.optaplanner.examples.timetable.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@PlanningEntity // Optaplanner changes the @PlanningEntity with its @PlanningVariable during solving
public class Lesson {

    @PlanningId
    @Id @GeneratedValue
    private Long id;

    // subject, teacher and studentGroup define the lesson
    private String subject;
    private String teacher;
    private String studentGroup;

    @PlanningVariable
    @ManyToOne // a timeslot can be used by all/many lessons, but one single lesson is scheduled to one single timeslot
    private Timeslot timeslot;

    @PlanningVariable
    @ManyToOne // a room can be used by all/many lessons, but one single lesson occupies one single room
    private Room room;

    // No-arg constructor required for Hibernate and OptaPlanner
    public Lesson() {
    }

    public Lesson(String subject, String teacher, String studentGroup) {
        this.subject = subject;
        this.teacher = teacher;
        this.studentGroup = studentGroup;
    }

    public Lesson(long id, String subject, String teacher, String studentGroup) {
        this(subject, teacher, studentGroup);
        this.id = id;
    }

    public Lesson(long id, String subject, String teacher, String studentGroup, Timeslot timeslot, Room room) {
        this(subject, teacher, studentGroup);
        this.id = id;
        this.timeslot = timeslot;
        this.room = room;
    }

    @Override
    public String toString() {
        return subject + "(" + id + ")";
    }

    // ************************************************************************
    // Getters and setters
    // ************************************************************************

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
