package ae.tutorme.dao.imp;

import ae.tutorme.dao.ResponseDAO;
import ae.tutorme.model.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by almehairbi on 2/25/17.
 */
public class ResponseDAOImp implements ResponseDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void saveResponse(Response response) {

        Session session = sessionFactory.getCurrentSession();
        session.save(response);
        session.flush();

    }
}
