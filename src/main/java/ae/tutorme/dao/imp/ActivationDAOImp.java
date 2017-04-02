package ae.tutorme.dao.imp;

import ae.tutorme.dao.ActivationDAO;
import ae.tutorme.model.Activation;

import org.hibernate.Query;
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

	@Override
	public void deleteActivation(int id) {
		Session session = sessionFactory.getCurrentSession();
        String querry = "delete from ae.tutorme.model.Activation act where act.id = :id";
        Query query = session.createQuery(querry);
        query.setParameter("id", id);
        query.executeUpdate();
	}

	@Override
	public Activation getById(int id) {
		return (Activation) sessionFactory.getCurrentSession().get(Activation.class, id);
	}
}
