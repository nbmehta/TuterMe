package ae.tutorme.model;

import javax.persistence.*;

/**
 * Created by almehairbi on 2/23/17.
 */
@Entity
@Table(name = "RATE")
public class Rate {

    @Id
    @GeneratedValue
    @Column(name = "RATE_ID")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Course course;

    @Column(name = "RATING")
    private double rating;
    
    public Rate(int id, Student student, Course course, double rating) {
		super();
		this.id = id;
		this.student = student;
		this.course = course;
		this.rating = rating;
	}

	public Rate() {
        this.course =null;
        this.student = null;
        this.rating = 0;
    }

    public Rate(Student student, Course course, double rating) {
        this.student = student;
        this.course = course;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

