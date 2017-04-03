package ae.tutorme.dto;


import ae.tutorme.model.Course;
import ae.tutorme.model.Moderator;
import ae.tutorme.model.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by almehairbi on 2/17/17.
 */

public class ModeratorDTO extends UserDTO
{

    private Set<CourseDTO> courses = new HashSet<>();

    
    public ModeratorDTO() {
		// TODO Auto-generated constructor stub
	}


    public ModeratorDTO(User user) {
        super(user);
        Moderator m = (Moderator) user;
        this.courses = converter(m.getCourses());
    }

    public Set<CourseDTO> converter(Set<Course> courses) {
        Set<CourseDTO> coursesDTO = null;
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
