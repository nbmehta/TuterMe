package ae.tutorme.dao.imp;

import ae.tutorme.dao.StudentDAO;
import ae.tutorme.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by almehairbi on 2/28/17.
 */

@Repository
@Transactional
public class StudentDAOImp implements StudentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.save(student);
        session.flush();
    }

    @Override
    public Student getStudentById(int id) {

        Session session = sessionFactory.getCurrentSession();
        Student student = (Student) session.get(Student.class, id);
        session.flush();
        return student;
    }

    @Override
    public void updateStudentProfile(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(student);
        session.flush();
    }
}
