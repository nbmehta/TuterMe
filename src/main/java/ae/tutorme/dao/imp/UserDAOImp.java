package ae.tutorme.dao.imp;

import ae.tutorme.dao.UserDAO;
import ae.tutorme.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();

        session.close();
    }
}
