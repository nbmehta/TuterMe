package ae.tutorme.dto;

import ae.tutorme.dao.CourseDAO;
import ae.tutorme.model.Course;
import ae.tutorme.model.Instructor;
import ae.tutorme.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by almehairbi on 2/17/17.
 */

public class InstructorDTO extends UserDTO
{

    private Set<CourseDTO> courses = new HashSet<>();

    public InstructorDTO() {
		// TODO Auto-generated constructor stub
	}

    public InstructorDTO(User user) {
        super(user);
        this.courses = courseConverter(((Instructor) user).getCourses());
    }

    public Set<CourseDTO> courseConverter(Set<Course> courses) {
        Set<CourseDTO> coursesDTO = new HashSet<>();
        for (Course c : courses) {
            CourseDTO courseDTO = new CourseDTO(c);
            coursesDTO.add(courseDTO);
        }
        return coursesDTO;
    }

    public Set<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseDTO> courses) {
        this.courses = courses;
    }
}
