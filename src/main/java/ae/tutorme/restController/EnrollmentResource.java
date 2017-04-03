package ae.tutorme.restController;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ae.tutorme.dao.EnrollmentDAO;
import ae.tutorme.dto.EnrollmentDTO;
import ae.tutorme.dto.converter.Converter;
import ae.tutorme.model.Enrollment;
import ae.tutorme.utils.Helpers;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentResource {
private static final Logger logger = Logger.getLogger(EnrollmentResource.class);
	
	@Autowired
	private EnrollmentDAO enrollmentDAO;
	
	@Autowired
	private Converter converter;
	
	@RequestMapping(value="/add", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addEnrollment(@RequestBody EnrollmentDTO Enrollment) {
		try {
			Enrollment enroll = enrollmentDAO.saveEnrollment(converter.toEnrollment(Enrollment));
			return new ResponseEntity<>(new EnrollmentDTO(enroll), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("addEnrollment()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/id/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEnrollment(@PathVariable("id") int id) {
		try {
			EnrollmentDTO enrollment = enrollmentDAO.getEnrollmentDTOById(id);
			if(enrollment != null)
				return new ResponseEntity<EnrollmentDTO>(enrollment, HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("getEnrollment()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateEnrollment(@PathVariable("id") int id, @RequestBody EnrollmentDTO enrollment) {
		try {
			EnrollmentDTO enrollmentDTO = enrollmentDAO.updateEnrollment(id, enrollment);
			return new ResponseEntity<EnrollmentDTO>(enrollmentDTO, enrollmentDTO == null ? null : HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("updateEnrollment()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteEnrollment(@RequestBody Map<String, Object> map) {
		try {
			enrollmentDAO.deleteEnrollment((int) map.get("id"));
			return new ResponseEntity<>(Helpers.returnSingleMessage("success"), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("deleteEnrollment()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
