package ae.tutorme.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ae.tutorme.dao.InstructorDAO;
import ae.tutorme.dao.UserDAO;
import ae.tutorme.dto.AdminDTO;
import ae.tutorme.dto.InstructorDTO;
import ae.tutorme.dto.ModeratorDTO;
import ae.tutorme.dto.StudentDTO;
import ae.tutorme.model.Admin;
import ae.tutorme.model.Instructor;
import ae.tutorme.model.Moderator;
import ae.tutorme.model.Student;
import ae.tutorme.model.User;

/**
 * Created by almehairbi on 2/17/17.
 */


@Repository
@Transactional
public class UserDAOImp implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private InstructorDAO instructorDAO;

    public User saveUser(User user) {

        Session session = sessionFactory.getCurrentSession();

        session.save(user);

        session.flush();

        return user;
    }

    @Override
    public User getUserById(int id) {

        Session session = sessionFactory.openSession();
        User user = (User) session.get(User.class, id);

        session.flush();
        return user;

    }

    @Override
    public User getUserBuUserName(String userName) {
        User user = null;
        Session session = sessionFactory.getCurrentSession();
        String querry = "from ae.tutorme.model.User U where U.userName = '" + userName+"'";
        Query query = session.createQuery(querry);
        List<User> users = query.list();
        if(users.size() > 0 && users.size() < 2)
        {
            user = users.get(0);
        }
        return user;
    }

    @Override
    public void updateProfile(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        session.flush();
    }

	@Override
	public void deleteUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(User.class, id));
	}

	@Override
	public void deleteUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(user);
		session.delete(user);
	}

	@Override
	public AdminDTO updateAdmin(int id, AdminDTO admin) {
		Session session = sessionFactory.getCurrentSession();
		
		Admin adminFull = (Admin) session.get(Admin.class, id);
		
		adminFull.setEnabled(admin.isEnabled());
		adminFull.setMessages(admin.getMessages());
		adminFull.setName(admin.getName());
		adminFull.setPassword(admin.getPassword());
		adminFull.setUserName(admin.getUserName());
		
		session.update(adminFull);
		return new AdminDTO(adminFull);
	}

	@Override
	public InstructorDTO updateInstructor(int id, InstructorDTO instructor) {
		Session session = sessionFactory.getCurrentSession();
		
		Instructor instructorFull = (Instructor) session.get(Instructor.class, instructor.getUserId());
		
		instructorFull.setEnabled(instructor.isEnabled());
		instructorFull.setMessages(instructor.getMessages());
		instructorFull.setName(instructor.getName());
		instructorFull.setPassword(instructor.getPassword());
		instructorFull.setUserName(instructor.getUserName());
		
		session.update(instructorFull);
		return new InstructorDTO(instructorFull);
	}

	@Override
	public ModeratorDTO updateModerator(int id, ModeratorDTO moderator) {
		Session session = sessionFactory.getCurrentSession();
		
		Moderator moderatorFull = (Moderator) session.get(Moderator.class, moderator.getUserId());
		
		moderatorFull.setEnabled(moderator.isEnabled());
		moderatorFull.setMessages(moderator.getMessages());
		moderatorFull.setName(moderator.getName());
		moderatorFull.setPassword(moderator.getPassword());
		moderatorFull.setUserName(moderator.getUserName());
		
		session.update(moderatorFull);
		return new ModeratorDTO(moderatorFull);
	}

	@Override
	public StudentDTO updateStudent(int id, StudentDTO student) {
		Session session = sessionFactory.getCurrentSession();
		
		Student studentFull = (Student) session.get(Student.class, id);
		
		studentFull.setEnabled(student.isEnabled());
		studentFull.setMessages(student.getMessages());
		studentFull.setName(student.getName());
		studentFull.setPassword(student.getPassword());
		studentFull.setUserName(student.getUserName());
		
		session.update(studentFull);
		return new StudentDTO(studentFull);
	}


}
