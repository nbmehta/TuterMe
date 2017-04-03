package ae.tutorme.model;

import java.util.Set;

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

    public Admin(int userId, String userName, String password, boolean enabled, String name, Activation activation, Authorization authorization, Set<Message> messages) {
		super(userId, userName, password, enabled, name, activation, authorization, messages);
	}

	public Admin() {
    }

    public Admin(String userName, String password) {
        super(userName, password);
    }

    public Admin(String userName, String password, boolean enabled, Activation activation) {
        super(userName, password, enabled, activation);
    }
    
    public Admin(String userName, String password, boolean enabled, String name, Activation activation, Authorization authorization) {
		super(userName, password, enabled, name, activation, authorization);
	}

	public Admin(String userName, String password, boolean enabled, Activation activation, Authorization authorization) {
        super(userName, password, enabled, activation, authorization);
    }
	
	
}
