package ae.tutorme.dto;



import ae.tutorme.model.Category;
import ae.tutorme.model.Course;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by almehairbi on 2/23/17.
 */



public class CategoryDTO implements Serializable {

    private int categoryId;
    private String name;
    private Set<CourseDTO> courses ;



    public CategoryDTO(Category category) {
        this.categoryId = category.getCategoryId();
        this.name = category.getName();
        this.courses = converter(category.getCourses());
    }

    public Set<CourseDTO> converter(Set<Course> courses) {
        Set<CourseDTO> coursesDTO = null;
        for (Course c : courses) {
            CourseDTO courseDTO = new CourseDTO(c);
            coursesDTO.add(courseDTO);
        }
        return coursesDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Set<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseDTO> courses) {
        this.courses = courses;
    }
}

