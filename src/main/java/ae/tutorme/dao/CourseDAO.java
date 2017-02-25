package ae.tutorme.dao;

import ae.tutorme.model.Course;

import java.util.List;

/**
 * Created by almehairbi on 2/18/17.
 */
public interface CourseDAO {

    void saveCourse(Course course);

    Course getCourseById(int id);

    List<Course> getCourseByTeacherId(int id);

    void updateCourse(Course course);
}
