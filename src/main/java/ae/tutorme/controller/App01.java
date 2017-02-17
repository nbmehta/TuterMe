package ae.tutorme.controller;

import ae.tutorme.dao.UserDAO;
import ae.tutorme.model.Activation;
import ae.tutorme.model.Admin;
import ae.tutorme.model.Authorization;
import ae.tutorme.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by almehairbi on 2/17/17.
 */

@Controller
public class App01 {


    @Autowired
    private  UserDAO userDAO;

    @RequestMapping(value = "/adduser")
    public String addUser() {

        System.out.println("Hibernate one to one (Annotation)");
        User user = new Admin();
        user.setPassword("123");
        user.setUserName("123");

        Authorization auth = new Authorization();
        auth.setRole("ROLE_ADMIN");
        auth.setUser(user);

        user.setAuthorization(auth);

        Activation activ = new Activation();
        activ.setUser(user);
        activ.setUuid("uuid");

        user.setActivation(activ);
        userDAO.saveUser(user);

        System.out.println("Done");

        return "testJSP1";
    }

    @RequestMapping("/")
    public String home() {

        return "testJSP1";
    }
}
