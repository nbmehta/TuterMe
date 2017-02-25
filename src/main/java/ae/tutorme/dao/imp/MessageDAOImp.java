package ae.tutorme.dao.imp;

import ae.tutorme.dao.MessageDAO;
import ae.tutorme.model.Message;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * Created by almehairbi on 2/25/17.
 */
public class MessageDAOImp implements MessageDAO {

    @Autowired
    SessionFactory sessionFactory;
    
    @Override
    public void saveMessage(Message message) {
        Session session = sessionFactory.getCurrentSession();
        session.save(message);
        session.flush();
    }

    @Override
    public List<Message> getMessagesBySenderId(int id) {
        Session session = sessionFactory.getCurrentSession();
        String querry = "from ae.tutorme.model.Message message where message.user.userId = '" + id+"'";
        Query query = session.createQuery(querry);
        List<Message> messages = query.list();
        session.flush();
        return messages;
    }

    @Override
    public List<Message> getMessageByReciverId(int id) {
        Session session = sessionFactory.getCurrentSession();
        String querry = "from ae.tutorme.model.Message message where message.reciverId = '" + id+"'";
        Query query = session.createQuery(querry);
        List<Message> messages = query.list();
        session.flush();
        return messages;
    }

    }

