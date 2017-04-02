package ae.tutorme.dao.imp;

import ae.tutorme.dao.CourseDAO;
import ae.tutorme.dto.CourseDTO;
import ae.tutorme.dto.EnrollmentDTO;
import ae.tutorme.model.Course;
import ae.tutorme.model.Enrollment;

import org.hibernate.Hibernate;
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
    public List<Course> getCourseByTeacherId(int id) {
        Session session = sessionFactory.getCurrentSession();
        String querry = "from ae.tutorme.model.Course Course where Course.instructor.userId = '" + id+"'";
        Query query = session.createQuery(querry);
        List<Course> courseList = query.list();
        return courseList;
    }

    @Override
    public void updateCourse(Course course) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(course);
        session.flush();
    }

	@Override
	public void deleteCourse(int id) {
		Session session = sessionFactory.getCurrentSession();
        String querry = "delete from ae.tutorme.model.Course c where c.courseId = :id";
        Query query = session.createQuery(querry);
        query.setParameter("id", id);
        query.executeUpdate();
	}

	@Override
	public CourseDTO getCourseDTOById(int id) {
		Course course = getCourseById(id);
		return course == null ? null : new CourseDTO(course);
	}

	@Override
	public CourseDTO updateCourse(int id, CourseDTO course) {
		Course courseFull = getCourseById(id);
		
		if(courseFull != null) {
			courseFull.setDescription(course.getDescription());
			courseFull.setEnabled(course.isEnabled());
			courseFull.setPrice(course.getPrice());
			courseFull.setName(course.getName());
			
			updateCourse(courseFull);
			return new CourseDTO(courseFull);
		} else {
			return null;
		}
	}
}
