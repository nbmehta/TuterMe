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
}
