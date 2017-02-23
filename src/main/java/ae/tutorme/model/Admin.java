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


    public Admin() {
    }

    public Admin(String userName, String password) {
        super(userName, password);
    }

    public Admin(String userName, String password, boolean enabled, Activation activation) {
        super(userName, password, enabled, activation);
    }

    public Admin(String userName, String password, boolean enabled, Activation activation, Authorization authorization) {
        super(userName, password, enabled, activation, authorization);
    }
}
