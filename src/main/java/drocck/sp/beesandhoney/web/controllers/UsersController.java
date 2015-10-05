package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Robert Wilk
 *         Created on 10/4/2015.
 */
@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    public ModelAndView getUsersPage() {
        return new ModelAndView("users", "users", userService.findAll());
    }
}
