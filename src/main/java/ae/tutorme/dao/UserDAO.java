package ae.tutorme.dao;

import ae.tutorme.model.Course;
import ae.tutorme.model.User;

import javax.jws.soap.SOAPBinding;

/**
 * Created by almehairbi on 2/17/17.
 */
public interface UserDAO {

    void saveUser(User user);

    User getUserById(int id);

    User getUserBuUserName(String userName);

    void updateProfile(User user);
}
