package ae.tutorme.model;

import org.hibernate.annotations.ForeignKey;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by almehairbi on 2/17/17.
 */

@Entity
@Table(name = "INSTRUCTOR")
public class Instructor extends User
{
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "instructor")
    @ForeignKey(name="INSTRUCTOR_ID")
    private Set<Course> courses  = new HashSet<>(0);


    public Instructor() {
    }

    public Instructor(String userName, String password) {
        super(userName, password);
    }

    public Instructor(String userName, String password, boolean enabled, Activation activation) {
        super(userName, password, enabled, activation);
    }

    public Instructor(String userName, String password, boolean enabled, Activation activation, Authorization authorization) {
        super(userName, password, enabled, activation, authorization);
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
