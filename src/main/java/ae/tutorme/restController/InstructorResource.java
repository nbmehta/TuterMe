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
import ae.tutorme.dto.InstructorDTO;
import ae.tutorme.dto.InstructorDTO;
import ae.tutorme.dto.converter.Converter;
import ae.tutorme.model.Instructor;
import ae.tutorme.model.Instructor;
import ae.tutorme.model.User;
import ae.tutorme.utils.Helpers;

@RestController
@RequestMapping("/instructor")
public class InstructorResource {
	private static final Logger LOG = Logger.getLogger(InstructorResource.class);

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private Converter converter;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<?> addInstructor(@RequestBody InstructorDTO instructor) {
		try {
			User user = userDAO.saveUser(converter.toInstructor(instructor));
			return new ResponseEntity<>(new InstructorDTO(user), HttpStatus.OK);
		} catch (Exception ex) {
			LOG.error("addInstructor()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> updateInstructor(@PathVariable int id, @RequestBody InstructorDTO instructor) {
		try {
			InstructorDTO instructorDTO = userDAO.updateInstructor(id, instructor);
			return new ResponseEntity<InstructorDTO>(instructorDTO, instructorDTO == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
		} catch (Exception ex) {
			LOG.error("updateInstructor()", ex);
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
