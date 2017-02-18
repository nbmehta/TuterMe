package ae.tutorme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by almehairbi on 2/17/17.
 */

@Entity
@Table(name = "ADMIN")
public class Admin extends User
{

    @Column(name = "LEVEL")
    private int level;

    public Admin(String userName, String password, Activation activation, Authorization authorization) {
        super(userName, password, activation, authorization);
    }

    public Admin(String userName, String password, Activation activation) {
        super(userName, password, activation);
    }

    public Admin(int userId, String userName, String password) {
        super(userId, userName, password);
    }

    public Admin() {
        super();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
