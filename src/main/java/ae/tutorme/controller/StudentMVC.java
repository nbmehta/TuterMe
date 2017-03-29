package ae.tutorme.controller;

import ae.tutorme.dao.StudentDAO;
import ae.tutorme.dao.UserDAO;
import ae.tutorme.model.Student;
import ae.tutorme.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by almehairbi on 2/28/17.
 */
@Controller
@RequestMapping("/customer")
public class StudentMVC {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/new")
    public String saveStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/new")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentDAO.saveStudent(student);
        return "";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/update")
    public String updateStudent(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User student = null;

        if (principal instanceof Student) {
            student = (Student) principal;
        } else {
            String username = principal.toString();
            student = userDAO.getUserBuUserName(username);
        }

        model.addAttribute("student", student);
        return "";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String updateStudent(@ModelAttribute("student") Student student) {
        studentDAO.updateStudentProfile(student);
        return "";
    }


}

