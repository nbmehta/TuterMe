package ae.tutorme.model;

import javax.persistence.*;

/**
 * Created by almehairbi on 2/17/17.
 */

@Entity
@Table(name = "USER")
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

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Activation activation;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Authorization authorization;

    public User(String userName, String password, Activation activation, Authorization authorization) {
        this.userName = userName;
        this.password = password;
        this.activation = activation;
        this.authorization = authorization;
    }

    public User(String userName, String password, Activation activation) {
        this.userName = userName;
        this.password = password;
        this.activation = activation;
    }

    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }



    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public User() {
        this(0, "", "");
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
