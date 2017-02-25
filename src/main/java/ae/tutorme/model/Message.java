package ae.tutorme.model;

import javax.persistence.*;

/**
 * Created by almehairbi on 2/23/17.
 */
@Entity
@Table(name = "MESSAGE")
public class Message {

    @Id
    @GeneratedValue
    @Column(name = "MESSAGE_ID")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;

    @Column(name = "RECEIVER_ID")
    private int reciverId;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "BODY")
    private String body;

    public Message() {
        this.body = "";
        this.subject = "";
        this.reciverId = 0;
    }

    public Message(int reciverId, String subject, String body) {
        this(subject, body);
        this.reciverId = reciverId;

    }

    public Message(String subject, String body) {
        this();
        this.subject = subject;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getReciverId() {
        return reciverId;
    }

    public void setReciverId(int reciverId) {
        this.reciverId = reciverId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
