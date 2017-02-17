package ae.tutorme.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by almehairbi on 2/17/17.
 */

@Entity
@Table(name = "INSTRUCTOR")
public class Instructor extends User
{
    public Instructor(String userName, String password, Activation activation) {
        super(userName, password, activation);
    }

    public Instructor(int userId, String userName, String password) {
        super(userId, userName, password);
    }

    public Instructor() {
        super();
    }
}
