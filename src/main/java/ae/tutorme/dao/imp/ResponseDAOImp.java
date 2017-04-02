package ae.tutorme.dao.imp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ae.tutorme.dao.ResponseDAO;
import ae.tutorme.dto.ResponseDTO;
import ae.tutorme.model.Response;

/**
 * Created by almehairbi on 2/25/17.
 */
@Repository
@Transactional
public class ResponseDAOImp implements ResponseDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void saveResponse(Response response) {

        Session session = sessionFactory.getCurrentSession();
        session.save(response);
        session.flush();

    }
    
    @Override
	public void updateResponse(Response response) {
		Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(response);
        session.flush();
	}


	@Override
	public Response getById(int id) {
		return (Response) sessionFactory.getCurrentSession().get(Response.class, id);
	}


	@Override
	public ResponseDTO getResponseDTOById(int id) {
		Response response = getById(id);
		return response == null ? null : new ResponseDTO(getById(id));
	}


	@Override
	public ResponseDTO updateResponse(int id, ResponseDTO response) {
		Response responseFull = getById(response.getId());
		
		if(responseFull != null) {
			responseFull.setText(response.getText());
			
			updateResponse(responseFull);
			return new ResponseDTO(responseFull);
		} else {
			return null;
		}
	}


	@Override
	public void deleteResponse(int id) {
		Session session = sessionFactory.getCurrentSession();
        String querry = "delete from ae.tutorme.model.Response c where c.id = :id";
        Query query = session.createQuery(querry);
        query.setParameter("id", id);
        query.executeUpdate();
	}
}
