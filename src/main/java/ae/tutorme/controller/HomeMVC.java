package ae.tutorme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ali AL-Zaabi on 2/17/2017.
 */
@Controller
public class HomeMVC {

    @RequestMapping("/")
    public String home()
    {
        return "index";
    }
}
