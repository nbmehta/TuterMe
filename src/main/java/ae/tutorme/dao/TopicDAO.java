package ae.tutorme.dao;

import ae.tutorme.dto.TopicDTO;
import ae.tutorme.model.Topic;

/**
 * Created by almehairbi on 2/25/17.
 */
public interface TopicDAO {

    Topic saveTopic(Topic topic);

    void updateTopic(Topic topic);

    Topic getById(int id);
    
    TopicDTO getTopicDTOById(int id);

    TopicDTO updateTopic(int id, TopicDTO topic);
    
    void deleteTopic(int id);
}
