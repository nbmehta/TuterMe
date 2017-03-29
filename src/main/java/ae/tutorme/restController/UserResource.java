package ae.tutorme.restController;

import ae.tutorme.dao.CourseDAO;
import ae.tutorme.dao.UserDAO;
import ae.tutorme.dto.*;
import ae.tutorme.model.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by almehairbi on 3/28/17.
 */

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CourseDAO courseDAO;


    @RequestMapping(value = "/{userId}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void saveUser( )
    {

    };



    @RequestMapping(value = "/id/{userId}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUserById(@PathVariable("userId") int userId)
    {
        User user = userDAO.getUserById(userId);

        if(user != null)
        {
            if (user instanceof Instructor)
            {
                UserDTO instructorDTO = new InstructorDTO(user);
                return instructorDTO;
            } else if (user instanceof Student) {
                return new StudentDTO(user);
            } else if (user instanceof Admin) {
                return new AdminDTO(user);
            } else if (user instanceof Moderator) {
                return new ModeratorDTO(user);
            }
        }
        return null;

      //  sessionFactory.getCurrentSession().flush();
    };

    @RequestMapping(value = "/username/{userName}",method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE )
    public  ResponseEntity<User> getUserBuUserName(@PathVariable("userName") String userName)
    {
        User user = userDAO.getUserBuUserName(userName);

        if (user == null) {
            System.out.println("User mwith id " + userName + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    };

    @RequestMapping(value = "/{userId}",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public void  updateProfile(User user)
    {

    };
}
