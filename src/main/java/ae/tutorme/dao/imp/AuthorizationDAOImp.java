package ae.tutorme.dao.imp;

import ae.tutorme.dao.AuthorizationDAO;
import ae.tutorme.model.Authorization;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by almehairbi on 2/25/17.
 */
@Repository
@Transactional
public class AuthorizationDAOImp implements AuthorizationDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void saveAuthorization(Authorization authorization) {

        Session session = sessionFactory.getCurrentSession();
        session.save(authorization);
        session.flush();

    }

    @Override
    public Authorization getAuthorizationByUserName(String userName) {
        Session session = sessionFactory.getCurrentSession();
        String querry = "from ae.tutorme.model.Authorization Authorization where Authorization.user.userName = '" + userName+"'";
        Query query = session.createQuery(querry);
        List<Authorization> authorizations = query.list();
        if (authorizations.size() > 0 && authorizations.size() < 2) {

            return authorizations.get(0);
        }
        return null;
    }

	@Override
	public Authorization getById(int id) {
		return (Authorization) sessionFactory.getCurrentSession().get(Authorization.class, id);
	}

	@Override
	public void updateAuthorization(Authorization auth) {
		sessionFactory.getCurrentSession().update(auth);
	}

	@Override
	public void deleteAuthorization(int id) {
		Session session = sessionFactory.getCurrentSession();
        String querry = "delete from ae.tutorme.model.Authorization auth where auth.id = :id";
        Query query = session.createQuery(querry);
        query.setParameter("id", id);
        query.executeUpdate();
	}
}
