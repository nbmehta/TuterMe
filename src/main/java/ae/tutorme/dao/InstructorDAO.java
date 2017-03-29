package ae.tutorme.dao;

import ae.tutorme.model.Instructor;

/**
 * Created by almehairbi on 3/29/17.
 */
public interface InstructorDAO {

    void saveUser(Instructor user);

    Instructor getUserById(int id);

    Instructor getUserBuUserName(String userName);

    void  updateProfile(Instructor user);
}
