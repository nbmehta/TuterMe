

package ae.tutorme.dao.imp;

import ae.tutorme.dao.EnrollmentDAO;
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

    public void saveEnrollment(Enrollment enrollment) {
        Session session = sessionFactory.openSession();
        session.save(enrollment);
        session.flush();
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
}

