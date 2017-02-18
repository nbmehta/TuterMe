package ae.tutorme.model;

import javax.persistence.*;

/**
 * Created by almehairbi on 2/18/17.
 */

@Entity
@Table(name = "COURSE")
public class Course {

    @Id
    @GeneratedValue
    @Column(name = "COURSE_ID")
    private int courseId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSTRUCTOR", nullable = false)
    private Instructor instructor;

    @Column(name = "NAME")
    private String name;

    public Course() {
        this(null, "");
    }

    public Course(Instructor instructor, String name) {
        this.instructor = instructor;
        this.name = name;
    }

    public Course(String name) {
        this.name = name;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
