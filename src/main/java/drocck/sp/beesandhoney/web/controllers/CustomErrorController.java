package drocck.sp.beesandhoney.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Connor on 11/12/2015.
 *
 * Controller that defines how to handle when an error occurs.
 */
@Controller
public class CustomErrorController implements ErrorController {

    private final static String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    /**
     * Override method that determines where to send user when an error occurs.
     *
     * @return The path to send the user when an error occurs.
     */
    @Override
    public String getErrorPath() {
        return PATH;
    }

    /**
     * Function that is called whenever an error occurs.
     * Attaches an error message and stack trace to model to be displayed by error page
     *
     * @param request  The request to the server that caused an error.
     * @param response
     * @param model    The model to attach error message too
     * @return Path to error page html
     */
    @RequestMapping(value = PATH)
    public String error(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map<String, Object> error = getErrorAttributes(request, true);
        // check what type of error was caused
        int status = (int) error.get("status");
        String message;
        // add a custom message
        switch (status) {
            case 500: // server side error
                message = "Something went wrong on our end!";
                break;
            case 404: // page not found error
                message = "That page does not exist!";
                break;
            case 403: // forbidden page
                message = "You are not allowed to access this page!";
                break;
            default:
                message = "Something went wrong";
                break;
        }
        // send the message and error to the view
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        model.addAttribute("error", error);

        // go to error.html
        return "error";
    }

    /**
     * Function to extract error message from HttpServletRequest in the form of a Map.
     *
     * @param request           The request that contains the error.
     * @param includeStackTrace Boolean to determine whether or not to include stack trace.
     * @return Returns a map containing error information.
     */
    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }
}
