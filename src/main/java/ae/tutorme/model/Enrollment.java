package ae.tutorme.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by almehairbi on 2/23/17.
 */

@Entity
@Table(name = "ENROLLMENT")
public class Enrollment {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Course course;

    @Column(name = "ENROLLED_DATE")
    private Date enrolledDate;

    @Column(name = "AMOUNT_PAID")
    private double amountPaid;

    public Enrollment() {
        this.amountPaid = 0;
        this.course = null;
        this.student = null;
        this.enrolledDate = null;
    }
    
    public Enrollment(int id, Student student, Course course, Date enrolledDate, double amountPaid) {
		super();
		this.id = id;
		this.student = student;
		this.course = course;
		this.enrolledDate = enrolledDate;
		this.amountPaid = amountPaid;
	}

	public Enrollment(Date enrolledDate) {
        this();
        this.enrolledDate = enrolledDate;
    }

    public Enrollment(Student student, Course course, Date enrolledDate, double amountPaid) {
        this(enrolledDate);
        this.student = student;
        this.course = course;
        this.amountPaid = amountPaid;
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

    public Date getEnrolledDate() {
        return enrolledDate;
    }

    public void setEnrolledDate(Date enrolledDate) {
        this.enrolledDate = enrolledDate;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }
}

