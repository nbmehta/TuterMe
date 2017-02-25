package ae.tutorme.controller;

import ae.tutorme.dao.CategoryDAO;
import ae.tutorme.dao.CourseDAO;
import ae.tutorme.dao.UserDAO;
import ae.tutorme.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by almehairbi on 2/17/17.
 */

@Controller
public class App01 {


    @Autowired
    private  UserDAO userDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @RequestMapping(value = "/")
    public String addUser() {

        System.out.println("Hibernate one to one (Annotation)");
        Instructor user = new Instructor();
        user.setPassword("123");
        user.setUserName("123");
        user.setEnabled(true);


        Authorization auth = new Authorization();
        auth.setRole("ROLE_ADMIN");
        auth.setUser(user);

        user.setAuthorization(auth);

        Activation activ = new Activation();
        activ.setUser(user);
        activ.setactivationCode("uuid");

        user.setActivation(activ);
        userDAO.saveUser(user);

        System.out.println("Done");
        Category category = new Category("Hala");
        categoryDAO.saveCategory(category);

        Course c = new Course();
        c.setInstructor(user);
        c.setCategory(category);

        courseDAO.saveCourse(c);






        return "testJSP1";
    }

}
