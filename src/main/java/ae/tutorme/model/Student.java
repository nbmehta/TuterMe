package ae.tutorme.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by almehairbi on 2/17/17.
 */

@Entity
@Table(name = "STUDENT")
public class Student extends User
{
    public Student(String userName, String password, Activation activation, Authorization authorization) {
        super(userName, password, activation, authorization);
    }

    public Student(String userName, String password, Activation activation) {
        super(userName, password, activation);
    }

    public Student(int userId, String userName, String password) {
        super(userId, userName, password);
    }

    public Student() {
        super();
    }
}
