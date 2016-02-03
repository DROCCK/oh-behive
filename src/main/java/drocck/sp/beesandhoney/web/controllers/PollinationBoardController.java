package drocck.sp.beesandhoney.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Robert Wilk
 *         Created on 2/2/2016.
 */
@Controller
public class PollinationBoardController {

    @RequestMapping(value = "dashboard/pollination", method = RequestMethod.GET)
    public ModelAndView pollinationBoard() {
        return new ModelAndView("dashboard/pollination");
    }
}
