package ae.tutorme.dao.imp;

import ae.tutorme.dao.UserDAO;
import ae.tutorme.model.Course;
import ae.tutorme.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by almehairbi on 2/17/17.
 */


@Repository
@Transactional
public class UserDAOImp implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public void saveUser(User user) {

        Session session = sessionFactory.openSession();

        session.save(user);

        session.flush();


    }

    @Override
    public User getUserById(int id) {

        Session session = sessionFactory.getCurrentSession();
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


}
