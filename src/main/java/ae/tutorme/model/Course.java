package ae.tutorme.model;

import com.sun.istack.internal.Nullable;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by almehairbi on 2/18/17.
 */

@Entity
@Table(name = "COURSE")
public class Course {

    @Id
    @GeneratedValue
    @Column(name = "COURSE_ID")
    private int courseId;


    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Instructor instructor;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Moderator moderator;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ENABLED")
    private boolean enabled;

    @Formula("select sum(RATING)/count from RATE where COURSE_ID = ?")
    private double rating;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "course")
    private Set<Enrollment> enrollments = new HashSet<>(0);

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "course")
    private Set<Topic> topics = new HashSet<>(0);

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "course")
    private Set<Rate> rates = new HashSet<>(0);


    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "CATEGORY_ID")
    private Category category;


    public Course() {
        this.description = "";
        this.category = null;
        this.instructor = null;
        this.moderator = null;
        this.name = "";
        this.price = 0;
        this.rating = 0;
        this.enabled = false;
    }

    public Course(String name) {
        this();
        this.name = name;
    }

    public Course(Instructor instructor, String name) {
        this(name);
        this.instructor = instructor;
    }

    public Course(Instructor instructor, String name, Category category) {
        this(instructor, name);
        this.category = category;
    }

    public Course(Instructor instructor, Moderator moderator, String description, double price, String name, double rating, Category category) {
        this(instructor, name, category);
        this.moderator = moderator;
        this.description = description;
        this.price = price;
        this.rating = rating;
    }



    public Moderator getModerator() {
        return moderator;
    }

    public void setModerator(Moderator moderator) {
        this.moderator = moderator;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Set<Rate> getRates() {
        return rates;
    }

    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
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
