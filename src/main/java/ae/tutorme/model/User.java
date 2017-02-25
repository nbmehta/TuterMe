package ae.tutorme.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by almehairbi on 2/17/17.
 */

@Entity
@Table(name = "USER")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User
{

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ENABLED")
    private boolean enabled;

    @Column(name = "FULL_NAME")
    private String name;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Activation activation;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Authorization authorization;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    private Set<Message> messages = new HashSet<>(0);

    public User() {
        this.activation =null;
        this.authorization = null;
        this.enabled = false;
        this.password = "";
        this.userName = "";
    }

    public User(String userName, String password) {
        this();
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, boolean enabled, Activation activation) {
        this(userName, password);
        this.enabled = enabled;
        this.activation = activation;
    }

    public User(String userName, String password, boolean enabled, Activation activation, Authorization authorization) {
        this(userName, password, enabled, activation);
        this.authorization = authorization;
    }

    public User(String userName, String password, boolean enabled, String name, Activation activation, Authorization authorization) {
        this(userName, password, enabled, activation, authorization);
        this.name = name;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Activation getActivation() {
        return activation;
    }

    public void setActivation(Activation activation) {
        this.activation = activation;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
