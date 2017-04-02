package ae.tutorme.restController;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ae.tutorme.dao.CourseDAO;
import ae.tutorme.dto.CourseDTO;
import ae.tutorme.model.Course;
import ae.tutorme.utils.Helpers;

@Controller
@RequestMapping("/course")
public class CourseResource {
	private static final Logger logger = Logger.getLogger(CourseResource.class);
	
	@Autowired
	private CourseDAO courseDAO;
	
	@RequestMapping(value="/add", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addCourse(@RequestBody Course course) {
		try {
			courseDAO.saveCourse(course);
			return new ResponseEntity<CourseDTO>(new CourseDTO(course), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("addCourse()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/id/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCourse(@PathVariable("id") int id) {
		try {
			CourseDTO course = courseDAO.getCourseDTOById(id);
			if(course != null)
				return new ResponseEntity<CourseDTO>(course, HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("getCourse()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCourse(@PathVariable("id") int id, @RequestBody CourseDTO course) {
		try {
			CourseDTO courseFull = courseDAO.updateCourse(id, course);
			return new ResponseEntity<>(courseFull, courseFull == null ? null : HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("updateCourse()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteActivation(@RequestBody Map<String, Object> map) {
		try {
			courseDAO.deleteCourse((int) map.get("id"));
			return new ResponseEntity<>(Helpers.returnSingleMessage("success"), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("deleteActivation()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
