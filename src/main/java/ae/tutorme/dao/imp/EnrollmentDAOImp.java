

package ae.tutorme.dao.imp;

import ae.tutorme.dao.EnrollmentDAO;
import ae.tutorme.dto.EnrollmentDTO;
import ae.tutorme.model.Enrollment;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ali AL-Zaabi on 2/25/2017.
 */
@Repository
@Transactional
public class EnrollmentDAOImp implements EnrollmentDAO {

    @Autowired
    SessionFactory sessionFactory;

    public Enrollment saveEnrollment(Enrollment enrollment) {
        Session session = sessionFactory.openSession();
        session.save(enrollment);
        session.flush();
        return enrollment;
    }

    @Override
    public Enrollment getEnrollmentById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Enrollment enrollment = (Enrollment) session.get(Enrollment.class, id);
        session.flush();
        return enrollment;

    }

    @Override
    public List<Enrollment> getEnrollmentsByStudentId(int userId) {
        Enrollment enrollment = null;
        Session session = sessionFactory.getCurrentSession();
        String querry = "from ae.tutorme.model.Enrollment E where E.student.userId = '" + userId+"'";
        Query query = session.createQuery(querry);
        List<Enrollment> enrollments = query.list();

        return enrollments;
    }

	@Override
	public void updateEnrollment(Enrollment enrollment) {
		sessionFactory.getCurrentSession().update(enrollment);
	}

	@Override
	public void deleteEnrollment(int id) {
        Session session = sessionFactory.getCurrentSession();
        String querry = "delete from ae.tutorme.model.Enrollment E where E.id=:id";
        Query query = session.createQuery(querry)
        		.setParameter("id", id);
        query.executeUpdate();
	}

	@Override
	public EnrollmentDTO getEnrollmentDTOById(int id) {
		Enrollment enrollment = getEnrollmentById(id);
		return enrollment == null ? null : new EnrollmentDTO(enrollment);
	}

	@Override
	public EnrollmentDTO updateEnrollment(int id, EnrollmentDTO enrollment) {
		Enrollment enrollmentFull = getEnrollmentById(id);
		
		if(enrollmentFull != null) {
			enrollmentFull.setEnrolledDate(enrollment.getEnrolledDate());
			enrollmentFull.setAmountPaid(enrollment.getAmountPaid());
			
			updateEnrollment(enrollmentFull);
			return new EnrollmentDTO(enrollmentFull);
		} else {
			return null;
		}
	}
}

