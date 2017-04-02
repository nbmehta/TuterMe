package ae.tutorme.dao.imp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ae.tutorme.dao.TopicDAO;
import ae.tutorme.dto.TopicDTO;
import ae.tutorme.model.Lesson;
import ae.tutorme.model.Topic;

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

	@Override
	public Topic getById(int id) {
		return (Topic) sessionFactory.getCurrentSession().get(Topic.class, id);
	}

	@Override
	public TopicDTO getTopicDTOById(int id) {
		Topic topic = getById(id);
		return topic == null ? null : new TopicDTO(getById(id));
	}

	@Override
	public TopicDTO updateTopic(int id, TopicDTO topic) {
		Topic topicFull = getById(topic.getId());
		
		if(topicFull != null) {
			topicFull.setTopicName(topic.getTopicName());
			topicFull.setTopicNumber(topic.getTopicNumber());
			
			updateTopic(topicFull);
			return new TopicDTO(topicFull);
		} else {
			return null;
		}
	}

	@Override
	public void deleteTopic(int id) {
		Session session = sessionFactory.getCurrentSession();
        String querry = "delete from ae.tutorme.model.Topic c where c.id = :id";
        Query query = session.createQuery(querry);
        query.setParameter("id", id);
        query.executeUpdate();
	}
}
