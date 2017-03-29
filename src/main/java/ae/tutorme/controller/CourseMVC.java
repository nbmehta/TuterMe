package ae.tutorme.controller;

import ae.tutorme.dao.CourseDAO;
import ae.tutorme.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by almehairbi on 2/28/17.
 */

@Controller
@RequestMapping(value = "/course")
public class CourseMVC {

    @Autowired
    private CourseDAO courseDAO;

    @RequestMapping(method = RequestMethod.POST)
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseDAO.saveCourse(course);
        return "";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{courseId}")
    public String getCourseById(@PathVariable int courseId, Model model) {

        Course course = courseDAO.getCourseById(courseId);
        model.addAttribute("course", course);
        return "";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/instructor/{instructorId}")
    public String getCourseByInstructorId(@PathVariable int instructorId, Model model) {
        List<Course> courses = courseDAO.getCourseByTeacherId(instructorId);
        model.addAttribute("instructorCourses", courses);
        return "";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String updateCourse(@ModelAttribute("course") Course course) {
        courseDAO.updateCourse(course);
        return "";
    }
}

