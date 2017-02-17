package ae.tutorme.model;

import javax.persistence.*;
import javax.validation.groups.ConvertGroup;

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
    @JoinColumn(name = "USERNAME")
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
}
