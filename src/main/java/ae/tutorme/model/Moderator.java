package ae.tutorme.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by almehairbi on 2/17/17.
 */

@Entity
@Table(name = "MODERATOR")
public class Moderator extends User
{

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<>(0);


    public Moderator() {
    }

    public Moderator(String userName, String password) {
        super(userName, password);
    }

    public Moderator(String userName, String password, boolean enabled, Activation activation) {
        super(userName, password, enabled, activation);
    }

    public Moderator(String userName, String password, boolean enabled, Activation activation, Authorization authorization) {
        super(userName, password, enabled, activation, authorization);
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
