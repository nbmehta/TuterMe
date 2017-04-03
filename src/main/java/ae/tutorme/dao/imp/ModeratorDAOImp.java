package ae.tutorme.dao.imp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ae.tutorme.dao.ModeratorDAO;
import ae.tutorme.model.Moderator;

@Transactional
@Repository
public class ModeratorDAOImp implements ModeratorDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Moderator getModeratorById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Moderator) session.get(Moderator.class, id);
	}

}
