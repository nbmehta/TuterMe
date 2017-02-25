package ae.tutorme.dao.imp;

import ae.tutorme.dao.ActivationDAO;
import ae.tutorme.model.Activation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by almehairbi on 2/25/17.
 */
public class ActivationDAOImp implements ActivationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveActivation(Activation activation) {

        Session session = sessionFactory.getCurrentSession();
        session.save(activation);
        session.flush();

    }

    @Override
    public void updateActivation(Activation activation) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(activation);
        session.flush();
    }
}
