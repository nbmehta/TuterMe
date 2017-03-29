package ae.tutorme.dto;


import ae.tutorme.model.Enrollment;
import ae.tutorme.model.Rate;
import ae.tutorme.model.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by almehairbi on 2/17/17.
 */


public class StudentDTO extends UserDTO
{

    private Set<Enrollment> enrollments = new HashSet<>(0);

    private Set<Rate> rates = new HashSet<>(0);

    public StudentDTO(User user) {
        super(user);
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
