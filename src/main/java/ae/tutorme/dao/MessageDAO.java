package ae.tutorme.dao;


import ae.tutorme.dto.MessageDTO;
import ae.tutorme.model.Message;

import java.util.List;
import java.util.Set;

/**
 * Created by almehairbi on 2/25/17.
 */
public interface MessageDAO {

    void saveMessage(Message message);

    List<Message> getMessagesBySenderId(int id);

    List<Message> getMessageByReciverId(int id);

    Message getMessagesById(int id);

    MessageDTO getMessageDTOById(int id);
    
    void updateMessage(Message msg);
    
    MessageDTO updateMessage(int id, MessageDTO msg);
    
    void deleteMessage(int id);
}
