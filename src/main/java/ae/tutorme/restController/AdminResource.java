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
import ae.tutorme.dto.AdminDTO;
import ae.tutorme.dto.converter.Converter;
import ae.tutorme.model.Admin;
import ae.tutorme.model.User;

@RestController
@RequestMapping("/admin")
public class AdminResource {
	
	private static final Logger LOG = Logger.getLogger(AdminResource.class);

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private Converter converter;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<?> addAdmin(@RequestBody AdminDTO admin) {
		try {
			User user = userDAO.saveUser(converter.toAdmin(admin));
			return new ResponseEntity<>(new AdminDTO(user), HttpStatus.OK);
		} catch (Exception ex) {
			LOG.error("addAdmin()", ex);
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> updateAdmin(@PathVariable("id") int id, @RequestBody AdminDTO admin) {
		try {
			AdminDTO adminDTO = userDAO.updateAdmin(id, admin);
			return new ResponseEntity<AdminDTO>(adminDTO, adminDTO == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
		} catch (Exception ex) {
			LOG.error("updateAdmin()", ex);
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
