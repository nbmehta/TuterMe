package ae.tutorme.restController;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ae.tutorme.dao.TopicDAO;
import ae.tutorme.dto.TopicDTO;
import ae.tutorme.dto.converter.Converter;
import ae.tutorme.model.Topic;
import ae.tutorme.utils.Helpers;

@RestController
@RequestMapping("/topic")
public class TopicResource {
	private static final Logger logger = Logger.getLogger(TopicResource.class);
	
	@Autowired
	private TopicDAO topicDAO;
	
	@Autowired
	private Converter converter;
	
	@RequestMapping(value="/add", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addTopic(@RequestBody TopicDTO topic) {
		try {
			Topic t = topicDAO.saveTopic(converter.toTopic(topic));
			return new ResponseEntity<>(new TopicDTO(t), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("addTopic()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/id/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getTopic(@PathVariable("id") int id) {
		try {
			TopicDTO topic = topicDAO.getTopicDTOById(id);
			if(topic != null)
				return new ResponseEntity<TopicDTO>(topic, HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("getTopic()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateTopic(@PathVariable("id") int id, @RequestBody TopicDTO topic) {
		try {
			TopicDTO topicFull = topicDAO.updateTopic(id, topic);
			return new ResponseEntity<>(topicFull, topicFull == null ? null : HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("updateTopic()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteActivation(@RequestBody Map<String, Object> map) {
		try {
			topicDAO.deleteTopic((int) map.get("id"));
			return new ResponseEntity<>(Helpers.returnSingleMessage("success"), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("deleteActivation()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
