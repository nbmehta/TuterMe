package ae.tutorme.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by almehairbi on 2/23/17.
 */

@Entity
@Table(name = "RESPONSE")
public class Response {

    @Id
    @GeneratedValue
    @Column(name = "RESPONSE_ID")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Response> responses = new HashSet<>(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Response response;

    @Column(name = "RESPONSE_TEXT")
    private String text;

    public Response() {

        this.course =null;
        this.response = null;
        this.text = "";
        this.user = null;
    }

    public Response(Course course, User user) {
        this();
        this.course = course;
        this.user = user;
    }

    public Response(Course course, User user, Response response, String text) {
        this(course, user);
        this.response = response;
        this.text = text;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Response> getResponses() {
        return responses;
    }

    public void setResponses(Set<Response> responses) {
        this.responses = responses;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
