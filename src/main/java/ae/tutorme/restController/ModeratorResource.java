package ae.tutorme.restController;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ae.tutorme.dao.UserDAO;
import ae.tutorme.dto.ModeratorDTO;
import ae.tutorme.dto.ModeratorDTO;
import ae.tutorme.dto.converter.Converter;
import ae.tutorme.model.Moderator;
import ae.tutorme.model.User;
import ae.tutorme.utils.Helpers;

@RestController
@RequestMapping("/moderator")
public class ModeratorResource {
	private static final Logger LOG = Logger.getLogger(ModeratorResource.class);

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private Converter converter;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<?> addModerator(@RequestBody ModeratorDTO mod) {
		try {
			User u = userDAO.saveUser(converter.toModerator(mod));
			return new ResponseEntity<>(new ModeratorDTO(u), HttpStatus.OK);
		} catch (Exception ex) {
			LOG.error("addModerator()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> updateModerator(@PathVariable("id")int id, @RequestBody ModeratorDTO moderator) {
		try {
			ModeratorDTO moderatorDTO = userDAO.updateModerator(id, moderator);
			return new ResponseEntity<ModeratorDTO>(moderatorDTO, moderatorDTO == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
		} catch (Exception ex) {
			LOG.error("updateModerator()", ex);
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
