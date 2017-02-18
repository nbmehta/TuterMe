package ae.tutorme.dao.imp;

import ae.tutorme.dao.CourseDAO;
import ae.tutorme.model.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by almehairbi on 2/18/17.
 */

@Repository
@Transactional
public class CourseDAOImp implements CourseDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void saveCourse(Course course) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(course);

        session.getTransaction().commit();

        session.close();
    }
}
