package ae.tutorme.dao.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ae.tutorme.dao.MessageDAO;
import ae.tutorme.dto.MessageDTO;
import ae.tutorme.dto.converter.Converter;
import ae.tutorme.model.Message;

/**
 * Created by almehairbi on 2/25/17.
 */
@Repository
@Transactional
public class MessageDAOImp implements MessageDAO {

    @Autowired
    SessionFactory sessionFactory;
    
    @Autowired
    private Converter converter;
    
    @Override
    public Message saveMessage(Message message) {
        Session session = sessionFactory.getCurrentSession();
        session.save(message);
        session.flush();
        return message;
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

	@Override
	public Message getMessagesById(int id) {
		return (Message) sessionFactory.getCurrentSession().get(Message.class, id);
	}

	@Override
	public MessageDTO getMessageDTOById(int id) {
		Message msg = getMessagesById(id);
		return msg == null ? null : new MessageDTO(msg);
	}

	@Override
	public MessageDTO updateMessage(int id, MessageDTO msg) {
		Message msgFull = getMessagesById(msg.getId());
		Set<Message> msgs = new HashSet<>();
		for(MessageDTO d : msg.getMessages()) {
			msgs.add(d.getId() > 0 ? getMessagesById(d.getId()) : converter.toMessage(d));
		}
		
		if(msgFull != null) {
			msgFull.setBody(msg.getBody());
			msgFull.setReciverId(msg.getReciverId());
			msgFull.setSubject(msg.getSubject());
			msgFull.setMessages(msgs);
			
			updateMessage(msgFull);
			return new MessageDTO(msgFull);
		} else {
			return null;
		}	
	}

	@Override
	public void updateMessage(Message msg) {
		sessionFactory.getCurrentSession().update(msg);
	}

	@Override
	public void deleteMessage(int id) {
		Session session = sessionFactory.getCurrentSession();
        String querry = "delete from ae.tutorme.model.Message m where m.id = :id";
        Query query = session.createQuery(querry);
        query.setParameter("id", id);
        query.executeUpdate();
	}

}

