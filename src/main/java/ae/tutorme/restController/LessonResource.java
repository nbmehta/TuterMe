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

import ae.tutorme.dao.LessonDAO;
import ae.tutorme.dto.LessonDTO;
import ae.tutorme.model.Lesson;
import ae.tutorme.utils.Helpers;

@Controller
@RequestMapping("/lesson")
public class LessonResource {
	private static final Logger logger = Logger.getLogger(LessonResource.class);
	
	@Autowired
	private LessonDAO lessonDAO;
	
	@RequestMapping(value="/add", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addLesson(@RequestBody Lesson lesson) {
		try {
			lessonDAO.saveLesson(lesson);
			return new ResponseEntity<LessonDTO>(new LessonDTO(lesson), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("addLesson()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/id/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getLesson(@PathVariable("id") int id) {
		try {
			LessonDTO lesson = lessonDAO.getLessonDTOById(id);
			if(lesson != null)
				return new ResponseEntity<LessonDTO>(lesson, HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("getLesson()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateLesson(@PathVariable("id") int id, @RequestBody LessonDTO lesson) {
		try {
			LessonDTO lessonFull = lessonDAO.updateLesson(id, lesson);
			return new ResponseEntity<>(lessonFull, lessonFull == null ? null : HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("updateLesson()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteActivation(@RequestBody Map<String, Object> map) {
		try {
			lessonDAO.deleteLesson((int) map.get("id"));
			return new ResponseEntity<>(Helpers.returnSingleMessage("success"), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("deleteActivation()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
