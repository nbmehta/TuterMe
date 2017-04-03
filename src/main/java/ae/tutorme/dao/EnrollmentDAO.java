
package ae.tutorme.dao;

import ae.tutorme.dto.EnrollmentDTO;
import ae.tutorme.model.Enrollment;
import ae.tutorme.model.User;

import java.util.List;

/**
 * Created by almehairbi on 2/17/17.
 */
public interface EnrollmentDAO {

    Enrollment saveEnrollment(Enrollment enrollment);

    Enrollment getEnrollmentById(int id);
    
    EnrollmentDTO getEnrollmentDTOById(int id);

    List<Enrollment> getEnrollmentsByStudentId(int id);

    void updateEnrollment(Enrollment enrollment);
    
    EnrollmentDTO updateEnrollment(int id, EnrollmentDTO enrollment);
    
    void deleteEnrollment(int id);
}
