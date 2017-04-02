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

import ae.tutorme.dao.UserDAO;
import ae.tutorme.dto.AdminDTO;
import ae.tutorme.dto.InstructorDTO;
import ae.tutorme.dto.ModeratorDTO;
import ae.tutorme.dto.StudentDTO;
import ae.tutorme.dto.UserDTO;
import ae.tutorme.model.Admin;
import ae.tutorme.model.Instructor;
import ae.tutorme.model.Moderator;
import ae.tutorme.model.Student;
import ae.tutorme.model.User;
import ae.tutorme.utils.Helpers;

/**
 * Created by almehairbi on 3/28/17.
 */

@RestController
@RequestMapping("/user")
public class UserResource {
	
	private static final Logger LOG = Logger.getLogger(UserResource.class);

    @Autowired
    private UserDAO userDAO;


    @RequestMapping(value = "/add",method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public int saveUser(User user)
    {
    	userDAO.saveUser(user);
    	return user.getUserId();
    };

    @RequestMapping(value = "/id/{userId}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") int userId)
    {
        User user = userDAO.getUserById(userId);
        UserDTO responseEntity = null;

        if(user != null)
        {
            if (user instanceof Instructor)
            {
                UserDTO instructorDTO = new InstructorDTO(user);
                responseEntity = instructorDTO;
            } else if (user instanceof Student) {
            	responseEntity = new StudentDTO(user);
            } else if (user instanceof Admin) {
            	responseEntity = new AdminDTO(user);
            } else if (user instanceof Moderator) {
            	responseEntity = new ModeratorDTO(user);
            }
        }
        return new ResponseEntity<UserDTO>(responseEntity, user != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    };
    
    @RequestMapping(value = "/delete",method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public  ResponseEntity<?> deleteUser(@RequestBody Map<String, Object> map)
    {
    	try {
			userDAO.deleteUser((int) map.get("id"));
			return new ResponseEntity<>(Helpers.returnSingleMessage("Success"), HttpStatus.OK);
		} catch (Exception ex) {
			LOG.error("deleteUser()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    };

    @RequestMapping(value = "/username/{userName}",method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE )
    public  ResponseEntity<User> getUserBuUserName(@PathVariable("userName") String userName)
    {
    	return new ResponseEntity<User>(userDAO.getUserBuUserName(userName), HttpStatus.OK);
    };
}
