package ae.tutorme.dao.imp;

import ae.tutorme.dao.InstructorDAO;
import ae.tutorme.model.Instructor;
import ae.tutorme.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by almehairbi on 3/29/17.
 */

@Repository
@Transactional
public class InstructorDAOImp implements InstructorDAO {



    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void saveUser(Instructor user) {

        Session session = sessionFactory.getCurrentSession();

        session.save(user);

        session.flush();
    }

    @Override
    public Instructor getUserById(int id) {

        Session session = sessionFactory.getCurrentSession();
        Instructor user = (Instructor) session.get(User.class, id);

        session.flush();

        return user;
    }

    @Override
    public Instructor getUserBuUserName(String userName) {
        Instructor user = null;
        Session session = sessionFactory.getCurrentSession();
        String querry = "from ae.tutorme.model.Instructor U where U.userName = '" + userName+"'";
        Query query = session.createQuery(querry);
        List<Instructor> users = query.list();
        if(users.size() > 0 && users.size() < 2)
        {
            user = users.get(0);
        }
        return user;
    }

    @Override
    public void updateProfile(Instructor user) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        session.flush();
    }
}
