package drocck.sp.beesandhoney.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * @author Rob
 *         Created on 10/4/2015.
 */
@Controller
public class LoginController {

    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        return new ModelAndView("login", "error", error);
    }
}
