package ae.tutorme.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by almehairbi on 2/23/17.
 */

@Entity
@Table(name = "TOPIC")
public class Topic {

    @Id
    @GeneratedValue
    @Column(name = "TOPIC_ID")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Course course;

    @Column(name = "TOPIC_NAME")
    private String topicName;

    @Column(name = "TOPIC_NUMBER")
    private int topicNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    private Set<Lesson> lessons = new HashSet<>(0);

    public Topic() {
        this.course = null;
        this.topicName = "";
        this.topicNumber = 0;
    }

    public Topic(Course course) {
        this();
        this.course = course;
    }

    public Topic(Course course, String topicName, int topicNumber) {
        this(course);
        this.topicName = topicName;
        this.topicNumber = topicNumber;
    }

    public int getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getTopicNumber() {
        return topicNumber;
    }

    public void setTopicNumber(int topicNumber) {
        this.topicNumber = topicNumber;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }
}
