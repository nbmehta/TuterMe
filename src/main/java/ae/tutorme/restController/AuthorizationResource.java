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

import ae.tutorme.dao.AuthorizationDAO;
import ae.tutorme.dto.AuthorizationDTO;
import ae.tutorme.model.Authorization;
import ae.tutorme.utils.Helpers;

@RestController
@RequestMapping(value="/autorization")
public class AuthorizationResource {
	
	private static final Logger LOG = Logger.getLogger(Authorization.class);
	
	@Autowired
	private AuthorizationDAO authorizationDAO;
	
	@RequestMapping(value="/add", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addAuthorization(@RequestBody AuthorizationDTO auth) {
		try {
			Authorization full = authorizationDAO.saveAuthorization(auth);
			return new ResponseEntity<AuthorizationDTO>(new AuthorizationDTO(full), HttpStatus.OK);
		} catch (Exception ex) {
			LOG.error("addAuthorization()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/id/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAuthorization(@PathVariable("id") int id) {
		try {
			Authorization auth = authorizationDAO.getById(id);
			if(auth != null)
				return new ResponseEntity<AuthorizationDTO>(new AuthorizationDTO(), HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			LOG.error("getAuthorization()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/username/{name}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAuthorizationByName(@PathVariable("name") String name) {
		try {
			Authorization auth = authorizationDAO.getAuthorizationByUserName(name);
			if(auth != null)
				return new ResponseEntity<AuthorizationDTO>(new AuthorizationDTO(), HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			LOG.error("getAuthorizationByName()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateAuthorization(@PathVariable int id, @RequestBody AuthorizationDTO auth) {
		try {
			Authorization authFull = authorizationDAO.getById(id);
			
			authFull.setRole(auth.getRole());
			
			authorizationDAO.updateAuthorization(authFull);
			return new ResponseEntity<AuthorizationDTO>(new AuthorizationDTO(authFull), HttpStatus.OK);
		} catch (Exception ex) {
			LOG.error("updateAuthorization()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteAuthorization(@RequestBody Map<String, Object> map) {
		try {
			authorizationDAO.deleteAuthorization((int) map.get("id"));
			return new ResponseEntity<>(Helpers.returnSingleMessage("Success"), HttpStatus.OK);
		} catch (Exception ex) {
			LOG.error("deleteAuthorization()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
