package ae.tutorme.dao.imp;

import ae.tutorme.dao.CourseDAO;
import ae.tutorme.model.Course;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        session.save(course);
        session.flush();
    }

    @Override
    public Course getCourseById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Course course = (Course) session.get(Course.class, id);
        session.flush();
        return course;
    }

    @Override
    public Course getCourseByTeacherId(int id) {
        Course course = null;
        Session session = sessionFactory.getCurrentSession();

        String querry = "from ae.tutorme.model.Course Course where Course.instructor.Id = '" + id+"'";
        Query query = session.createQuery(querry);
        List<Course> courseList = query.list();
        if(courseList.size() > 0 && courseList.size() < 2)
        {
            course = courseList.get(0);
        }
        return course;
    }

    @Override
    public void updateCourse(Course course) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(course);
        session.flush();
    }
}
