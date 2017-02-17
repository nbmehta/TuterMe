package ae.tutorme.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by almehairbi on 2/17/17.
 */

@Entity
@Table(name = "MODERATOR")
public class Moderator extends User
{

    public Moderator(String userName, String password, Activation activation) {
        super(userName, password, activation);
    }

    public Moderator(int userId, String userName, String password) {
        super(userId, userName, password);
    }

    public Moderator() {
        super();
    }
}
