package ae.tutorme.dao.imp;

import ae.tutorme.dao.RateDAO;
import ae.tutorme.model.Rate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by almehairbi on 2/25/17.
 */
@Repository
@Transactional
public class RateDAOImp implements RateDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void saveRate(Rate rate) {

        Session session = sessionFactory.getCurrentSession();
        session.save(rate);
        session.flush();

    }
}
