package drocck.sp.beesandhoney.web.controllers;

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
        return new ModelAndView("user_create", "form", new UserCreateForm());
    }

    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form,
                                       BindingResult result) {
        if (result.hasErrors())
            return "user_create";
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            result.reject("email.exists", "Email already exists!");
            return "user_create";
        }
        return "redirect:/users";
    }

/*    @ModelAttribute("user")
    User construct() {
        return new User();
    }

    @RequestMapping("/user/create")
    public String create() {
        return "/user/create";
    }

    @RequestMapping(value = "user/create", method = RequestMethod.POST)
    public String create(@Valid User user, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors())
            return "user/create";
        user = userService.save(user);
        attributes.addFlashAttribute("successMessage", "Successfully created a new user");
        List<GrantedAuthority> authorities =
                AuthorityUtils.createAuthorityList("ROLE_USER");
        UserDetails userDetails = new org.springframework.security.core.userdetails
                .User(user.getUsername(), user.getPassword(), authorities);
        Authentication auth =
                new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "redirect:/";
    }*/
}
