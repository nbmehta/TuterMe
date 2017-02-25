package ae.tutorme.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by almehairbi on 2/23/17.
 */


@Entity
@Table(name = "CATEGORY")
public class   Category {

    @Id
    @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private int categoryId;

    @Column(name = "CATEGORY_NAME")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private Set<Course> courses = new HashSet<>(0);

    public Category() {
        this("");
    }

    public Category(String name) {
        this.name = name;
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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}

