package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.services.DataInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Robert Wilk
 *         Created on 9/26/2015.
 */
@Controller
public class IndexController {

    private static int many = 0;

    @Autowired
    private DataInitService dataInitService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, Principal principal) {

        // Uncomment if running on local DB.
         if (many++ == 0) dataInitService.init();

        String url = "index";
//        model.addAttribute("principal", principal);
//        int month = new GregorianCalendar().get(Calendar.MONTH);
//        if (month == Calendar.JANUARY || month == Calendar.FEBRUARY)
//            url = "redirect:dashboard/pollination";
//        if (month >= Calendar.MAY && month <= Calendar.DECEMBER)
//            url = "redirect:dashboard/beeboard";
//        if (month == Calendar.MARCH || month == Calendar.APRIL)
//            url = "redirect:dashboard/nucing";
        return url;
    }
}
