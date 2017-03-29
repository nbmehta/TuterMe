package ae.tutorme.dto;


import ae.tutorme.model.Enrollment;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by almehairbi on 2/23/17.
 */


public class EnrollmentDTO implements Serializable {


    private int id;
    private int studentId;
    private int courseId;
    private Date enrolledDate;
    private double amountPaid;

    public EnrollmentDTO(Enrollment enrollment) {
        this.id = enrollment.getId();
        this.amountPaid = enrollment.getAmountPaid();
        this.courseId = enrollment.getCourse().getCourseId();
        this.studentId = enrollment.getStudent().getUserId();
        this.enrolledDate = enrollment.getEnrolledDate();
    }


    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

