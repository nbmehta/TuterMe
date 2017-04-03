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

import ae.tutorme.dao.ActivationDAO;
import ae.tutorme.dto.ActivationDTO;
import ae.tutorme.model.Activation;
import ae.tutorme.utils.Helpers;

@RestController
@RequestMapping("/activation")
public class ActivationResource {
	
	private static final Logger logger = Logger.getLogger(ActivationResource.class);
	
	@Autowired
	private ActivationDAO activationDAO;
	
	@RequestMapping(value="/add", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addActivation(@RequestBody ActivationDTO activation) {
		try {
			Activation full = activationDAO.saveActivation(activation);
			return new ResponseEntity<>(new ActivationDTO(full), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("addActivation()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/id/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getActivation(@PathVariable("id") int id) {
		try {
			Activation activation = activationDAO.getById(id);
			if(activation != null)
				return new ResponseEntity<ActivationDTO>(new ActivationDTO(activation), HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("getActivation()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateActivation(@PathVariable("id")int id, @RequestBody ActivationDTO activation) {
		try {
			Activation activationFull = activationDAO.getById(id);
			
			activationFull.setactivationCode(activation.getActivationCode());
			activationFull.setExpiryDate(activation.getExpiryDate());
			
			activationDAO.updateActivation(activationFull);
			return new ResponseEntity<ActivationDTO>(new ActivationDTO(activationFull), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("updateActivation()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteActivation(@RequestBody Map<String, Object> map) {
		try {
			activationDAO.deleteActivation((int) map.get("id"));
			return new ResponseEntity<>(Helpers.returnSingleMessage("success"), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("deleteActivation()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
