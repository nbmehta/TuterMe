package ae.tutorme.dao.imp;

import ae.tutorme.dao.TopicDAO;
import ae.tutorme.model.Topic;
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
public class TopicDAOImp implements TopicDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void saveTopic(Topic topic) {

        Session session = sessionFactory.getCurrentSession();
        session.save(topic);
        session.flush();

    }

    @Override
    public void updateTopic(Topic topic) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(topic);
        session.flush();
    }
}
