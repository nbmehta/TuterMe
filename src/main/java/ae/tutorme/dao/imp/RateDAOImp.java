package ae.tutorme.dao.imp;

import ae.tutorme.dao.RateDAO;
import ae.tutorme.dto.RateDTO;
import ae.tutorme.dto.RateDTO;
import ae.tutorme.model.Rate;
import ae.tutorme.model.Rate;

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
public class RateDAOImp implements RateDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void saveRate(Rate rate) {

        Session session = sessionFactory.getCurrentSession();
        session.save(rate);
        session.flush();

    }


	@Override
	public void updateRate(Rate rate) {
		Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(rate);
        session.flush();
	}


	@Override
	public Rate getById(int id) {
		return (Rate) sessionFactory.getCurrentSession().get(Rate.class, id);
	}


	@Override
	public RateDTO getRateDTOById(int id) {
		Rate rate = getById(id);
		return rate == null ? null : new RateDTO(getById(id));
	}


	@Override
	public RateDTO updateRate(int id, RateDTO rate) {
		Rate rateFull = getById(rate.getId());
		
		if(rateFull != null) {
			rateFull.setRating(rate.getRating());
			
			updateRate(rateFull);
			return new RateDTO(rateFull);
		} else {
			return null;
		}
	}


	@Override
	public void deleteRate(int id) {
		Session session = sessionFactory.getCurrentSession();
        String querry = "delete from ae.tutorme.model.Rate c where c.id = :id";
        Query query = session.createQuery(querry);
        query.setParameter("id", id);
        query.executeUpdate();
	}
}
