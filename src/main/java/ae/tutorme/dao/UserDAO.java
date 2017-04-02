package ae.tutorme.dao;

import ae.tutorme.dto.AdminDTO;
import ae.tutorme.dto.InstructorDTO;
import ae.tutorme.dto.ModeratorDTO;
import ae.tutorme.dto.StudentDTO;
import ae.tutorme.model.User;

/**
 * Created by almehairbi on 2/17/17.
 */
public interface UserDAO {

	void saveUser(User user);

	User getUserById(int id);

	User getUserBuUserName(String userName);

	void updateProfile(User user);
	
	void deleteUser(int id);
	
	void deleteUser(User user);
	
	AdminDTO updateAdmin(int id, AdminDTO admin);
	
	InstructorDTO updateInstructor(int id, InstructorDTO instructor);
	
	ModeratorDTO updateModerator(int id, ModeratorDTO moderator);
	
	StudentDTO updateStudent(int id, StudentDTO student);
}
