package ae.tutorme.model;

import javax.persistence.*;

/**
 * Created by almehairbi on 2/17/17.
 */

@Entity
@Table(name = "AUTHORIZATION")
public class Authorization
{
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"),
            @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME") })
    private User user;


    @Column(name = "ROLE")
    private String role;

    public Authorization(User user, String role) {
        this.user = user;
        this.role = role;
    }



    public Authorization() {
        this(null, "");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
