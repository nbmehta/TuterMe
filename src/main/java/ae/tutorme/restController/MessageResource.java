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

import ae.tutorme.dao.MessageDAO;
import ae.tutorme.dto.MessageDTO;
import ae.tutorme.dto.converter.Converter;
import ae.tutorme.model.Message;
import ae.tutorme.utils.Helpers;

@RestController
@RequestMapping("/message")
public class MessageResource {
private static final Logger logger = Logger.getLogger(MessageResource.class);
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired
	private Converter converter;
	
	@RequestMapping(value="/add", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addMessage(@RequestBody MessageDTO message) {
		try {
			Message msg = messageDAO.saveMessage(converter.toMessage(message));
			return new ResponseEntity<>(new MessageDTO(msg), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("addMessage()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/id/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getMessage(@PathVariable("id") int id) {
		try {
			MessageDTO message = messageDAO.getMessageDTOById(id);
			if(message != null)
				return new ResponseEntity<MessageDTO>(message, HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("getMessage()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateMessage(@PathVariable("id") int id, @RequestBody MessageDTO message) {
		try {
			MessageDTO messageFull = messageDAO.updateMessage(id, message);
			return new ResponseEntity<>(messageFull, messageFull == null ? null : HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("updateMessage()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteActivation(@RequestBody Map<String, Object> map) {
		try {
			messageDAO.deleteMessage((int) map.get("id"));
			return new ResponseEntity<>(Helpers.returnSingleMessage("success"), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("deleteActivation()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
