package ae.tutorme.controller;

import ae.tutorme.dao.CategoryDAO;
import ae.tutorme.dao.CourseDAO;
import ae.tutorme.dao.UserDAO;
import ae.tutorme.model.Activation;
import ae.tutorme.model.Authorization;
import ae.tutorme.model.Instructor;
import ae.tutorme.model.User;
import ae.tutorme.service.imp.TutormeMailSender;
import ae.tutorme.utils.HashMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by almehairbi on 2/17/17.
 */

@Controller
public class App01 {

    @Autowired
    private TutormeMailSender tutormeMailSender;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/")
    public String addUser(Model model) {

//        User instructor = new Instructor();
//        instructor.setUserName("Ali");
//        instructor.setPassword("Ali");
//        Activation activation = new Activation();
//        activation.setactivationCode("12");
//        activation.setUser(instructor);
//        activation.setExpiryDate(new Date());
//        instructor.setActivation(activation);
//        Authorization authorization = new Authorization();
//        authorization.setRole("Teacher");
//        authorization.setUser(instructor);
//        instructor.setAuthorization(authorization);
//        userDAO.saveUser(instructor);
        return "testJSP1";
    }

}
