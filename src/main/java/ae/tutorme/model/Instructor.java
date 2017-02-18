package ae.tutorme.model;

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

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Course> courses  = new HashSet<Course>(0);

    public Instructor(String userName, String password, Activation activation, Authorization authorization) {
        super(userName, password, activation, authorization);
    }

    public Instructor(String userName, String password, Activation activation) {
        super(userName, password, activation);
    }

    public Instructor(int userId, String userName, String password) {
        super(userId, userName, password);
    }

    public Instructor() {
        super();
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
