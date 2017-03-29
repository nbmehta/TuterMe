
package ae.tutorme.dao;

import ae.tutorme.model.Enrollment;
import ae.tutorme.model.User;

import java.util.List;

/**
 * Created by almehairbi on 2/17/17.
 */
public interface EnrollmentDAO {

    void saveEnrollment(Enrollment enrollment);

    Enrollment getEnrollmentById(int id);

    List<Enrollment> getEnrollmentsByStudentId(int id);


}
