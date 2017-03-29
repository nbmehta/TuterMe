package ae.tutorme.controller;

import ae.tutorme.dao.CourseDAO;
import ae.tutorme.dao.EnrollmentDAO;
import ae.tutorme.dao.StudentDAO;
import ae.tutorme.dao.UserDAO;
import ae.tutorme.model.Course;
import ae.tutorme.model.Enrollment;
import ae.tutorme.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by almehairbi on 2/28/17.
 */

@Controller
@RequestMapping(value = "/enrollment")
public class EnrollmentMVC {

    @Autowired
    private EnrollmentDAO enrollmentDAO;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(method = RequestMethod.POST,value = "/{courseId}")
    public String saveEnrollment(@PathVariable int courseId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = null;

        if (principal instanceof Student) {
            student = (Student) principal;
        } else {
            String username = principal.toString();
            student = (Student) userDAO.getUserBuUserName(username);
        }

        Course course = courseDAO.getCourseById(courseId);

        Enrollment enrollment = new Enrollment(student, course, new Date(), 0);

        student.getEnrollments().add(enrollment);
        course.getEnrollments().add(enrollment);

        studentDAO.updateStudentProfile(student);
        courseDAO.updateCourse(course);
        enrollmentDAO.saveEnrollment(enrollment);

        return "";
    }

    @RequestMapping(method = RequestMethod.GET, value = "list")
    public String getEnrollments(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        Student student = (Student) userDAO.getUserBuUserName(userName);

        Set<Enrollment> enrollments = student.getEnrollments();

        model.addAttribute("enrollments", enrollments);

        return "";


    }


}
