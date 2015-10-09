package drocck.sp.beesandhoney.web.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import drocck.sp.beesandhoney.business.entities.DTOs.UserCreateForm;
import drocck.sp.beesandhoney.business.entities.User;
import drocck.sp.beesandhoney.business.entities.validators.UserCreateFormValidator;
import drocck.sp.beesandhoney.business.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Rob
 *         Created on 10/3/2015.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserCreateFormValidator userCreateFormValidator;

    @ModelAttribute("user")
    public User construct() {
        return new User();
    }

    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @PreAuthorize("@currentUserService.canAccessUser(principal, #id)")
    @RequestMapping("/user/{id}")
    public ModelAndView getUserPage(@PathVariable Long id) {
        return new ModelAndView("user", "user", userService.getUserById(id)
            .orElseThrow(() -> new NoSuchElementException("User not found!")));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
        return new ModelAndView("create", "form", new UserCreateForm());
    }

    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form,
                                       BindingResult result) {
        if (result.hasErrors())
            return "create";
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            result.reject("email.exists", "Email already exists!");
            return "create";
        }
        return "redirect:/users";
    }

/*    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public String create() {
        return "user/create";
    }*/

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String create(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/user/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user/read";
    }

    @RequestMapping(value = "/user/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("user",userService.findById(id));
        return "user/update";
    }

    @RequestMapping(value = "/user/updateUser/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user) {
        user.setId(id);
        userService.save(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user/delete";
    }

    @RequestMapping(value = "/user/confirmedDelete/{id}")
    public String confirmedDelete(@PathVariable Long id, @ModelAttribute("user") User user) {
        user.setId(id);
        userService.save(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }
}
