package drocck.sp.beesandhoney.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Robert Wilk
 *         Created on 9/26/2015.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, Principal principal) {
        // Calendar calendar = new GregorianCalendar();
        String url = "index";
        model.addAttribute("principal", principal);
        int month = new GregorianCalendar().get(Calendar.MONTH);
        if (month == Calendar.JANUARY || month == Calendar.FEBRUARY)
            url = "redirect:/dashboard/pollination";
        if (month >= Calendar.MAY && month <= Calendar.DECEMBER)
            url = "redirect:/dashboard/beeboard";
        if (month == Calendar.MARCH || month == Calendar.APRIL)
            url = "redirect:/dashboard/nucing";
        return url;
    }
}
