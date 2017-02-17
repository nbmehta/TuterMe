package ae.tutorme.model;

import javax.persistence.*;

/**
 * Created by almehairbi on 2/17/17.
 */

@Entity
@Table(name = "ACTIVATION")
public class Activation
{

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "UUID")
    private String uuid;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERNAME")
    private User user;

    public Activation(String uuid, User user) {
        this.uuid = uuid;
        this.user = user;
    }


    public Activation() {
        this("", null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
