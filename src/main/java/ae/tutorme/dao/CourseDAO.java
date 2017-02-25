package ae.tutorme.dao;

import ae.tutorme.model.Course;

/**
 * Created by almehairbi on 2/18/17.
 */
public interface CourseDAO {

    void saveCourse(Course course);

    Course getCourseById(int id);

    Course getCourseByTeacherId(int id);

    void updateCourse(Course course);
}
