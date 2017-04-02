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

import ae.tutorme.dao.UserDAO;
import ae.tutorme.dto.StudentDTO;
import ae.tutorme.dto.StudentDTO;
import ae.tutorme.model.Student;
import ae.tutorme.utils.Helpers;

@Controller
@RequestMapping("/student")
public class StudentResource {
	private static final Logger LOG = Logger.getLogger(StudentResource.class);

	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<?> addStudent(@RequestBody Student Student) {
		try {
			Student.getActivation().setUser(Student);
			Student.getAuthorization().setUser(Student);
			userDAO.saveUser(Student);
			return new ResponseEntity<>(new StudentDTO(Student), HttpStatus.OK);
		} catch (Exception ex) {
			LOG.error("addStudent()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> updateStudent(@PathVariable("id") int id, @RequestBody StudentDTO student) {
		try {
			StudentDTO studentDTO = userDAO.updateStudent(id, student);
			return new ResponseEntity<StudentDTO>(studentDTO, studentDTO == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
		} catch (Exception ex) {
			LOG.error("updateStudent()", ex);
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
