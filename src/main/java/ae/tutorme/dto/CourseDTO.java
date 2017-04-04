package ae.tutorme.dto;


import ae.tutorme.model.Course;
import ae.tutorme.model.Enrollment;
import ae.tutorme.model.Rate;
import ae.tutorme.model.Topic;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by almehairbi on 2/18/17.
 */


public class CourseDTO implements Serializable {


    private int courseId;
    private int instructorId;
    private int moderatorId;
    private String description;
    private double price;
    private String name;
    private boolean enabled;
    private double rating;
    private Set<EnrollmentDTO> enrollments = new HashSet<>();
    private Set<TopicDTO> topics = new HashSet<>();
    private Set<RateDTO> rates = new HashSet<>();
    private int categoryId;
    
    public CourseDTO() {
		// TODO Auto-generated constructor stub
	}


    public CourseDTO(Course course) {
        this.courseId = course.getCourseId();
        this.description = course.getDescription();
        this.categoryId = course.getCategory() != null ? course.getCategory().getCategoryId() : 0;
        this.instructorId = course.getInstructor() != null ? course.getInstructor().getUserId() : 0;
        this.moderatorId = course.getModerator() != null ? course.getModerator().getUserId() : 0;
        this.name = course.getName();
        this.price = course.getPrice();
        this.rating = course.getRating();
        this.enabled = course.isEnabled();
        this.enrollments = enroolmentConverter(course.getEnrollments());
        this.rates = rateConverter(course.getRates());
        this.topics = topicConverter(course.getTopics());
    }

    public Set<EnrollmentDTO> enroolmentConverter(Set<Enrollment> enrollments) {
        Set<EnrollmentDTO> enrollmentDTOs = new HashSet<>();
        for (Enrollment e : enrollments) {
            EnrollmentDTO enrollmentDTO = new EnrollmentDTO(e);
            enrollmentDTOs.add(enrollmentDTO);
        }
        return enrollmentDTOs;
    }

    public Set<TopicDTO> topicConverter(Set<Topic> topics) {
        Set<TopicDTO> topicDTOs =  new HashSet<>();
        for (Topic t : topics) {
            TopicDTO topicDTO = new TopicDTO(t);
            topicDTOs.add(topicDTO);
        }
        return topicDTOs;
    }
    public Set<RateDTO> rateConverter(Set<Rate> rates) {
        Set<RateDTO> rateDTOs =  new HashSet<>();
        for (Rate r : rates) {
            RateDTO rateDTO = new RateDTO(r);
            rateDTOs.add(rateDTO);
        }
        return rateDTOs;
    }


    public int getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(int moderatorId) {
        this.moderatorId = moderatorId;
    }

    public Set<TopicDTO> getTopics() {
        return topics;
    }

    public void setTopics(Set<TopicDTO> topics) {
        this.topics = topics;
    }

    public Set<EnrollmentDTO> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<EnrollmentDTO> enrollments) {
        this.enrollments = enrollments;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<RateDTO> getRates() {
        return rates;
    }

    public void setRates(Set<RateDTO> rates) {
        this.rates = rates;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
