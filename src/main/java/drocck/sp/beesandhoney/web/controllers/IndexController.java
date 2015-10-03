package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.User;
import drocck.sp.beesandhoney.security.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * @author Robert Wilk
 *         Created on 9/26/2015.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index( Model model, Principal principal) {
        return "index";
    }
}
