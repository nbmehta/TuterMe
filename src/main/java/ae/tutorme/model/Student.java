package ae.tutorme.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by almehairbi on 2/17/17.
 */

@Entity
@Table(name = "STUDENT")
public class Student extends User
{

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private Set<Enrollment> enrollments = new HashSet<>(0);

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private Set<Rate> rates = new HashSet<>(0);

    public Student() {
        super();
    }

    public Student(String userName, String password) {
        super(userName, password);
    }

    public Student(String userName, String password, boolean enabled, Activation activation) {
        super(userName, password, enabled, activation);
    }

    public Student(String userName, String password, boolean enabled, Activation activation, Authorization authorization) {
        super(userName, password, enabled, activation, authorization);
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public Set<Rate> getRates() {
        return rates;
    }

    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }


}
