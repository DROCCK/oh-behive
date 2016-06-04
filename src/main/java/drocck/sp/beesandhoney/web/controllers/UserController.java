package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.DTOs.UserCreateForm;
import drocck.sp.beesandhoney.business.entities.Role;
import drocck.sp.beesandhoney.business.entities.User;
import drocck.sp.beesandhoney.business.entities.validators.UserCreateFormValidator;
import drocck.sp.beesandhoney.business.services.RoleService;
import drocck.sp.beesandhoney.business.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Robert Wilk
 *         Created on 10/3/2015.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserCreateFormValidator userCreateFormValidator;

    @ModelAttribute("user")
    public User construct() {
        return new User();
    }

    @ModelAttribute("allRoles")
    public @ResponseBody List<Role> allRoles() {
        return roleService.findAll();
    }

    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @PreAuthorize("@currentUserService.canAccessUser(principal, #id)")
    @RequestMapping("user/{id}")
    public ModelAndView getUserPage(@PathVariable Long id) {
        return new ModelAndView("user", "user", userService.getUserById(id)
            .orElseThrow(() -> new NoSuchElementException("User not found!")));
    }

/*    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
        UserCreateForm form = new UserCreateForm();
        form.setAllRoles(roleService.findAll());
        return new ModelAndView("user/create", "form", new UserCreateForm());
    }*/

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
        return "redirect:list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("currentRoles", roleService.findAll());
        return "user/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute User user, BindingResult result, Model model) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.save(user);
        return "redirect:list";
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "user/read";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("user",userService.findOne(id));
        return "user/update";
    }

    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user) {
        user.setId(id);
        user.setRoles(userService.findOne(id).getRoles());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.update(user);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "user/delete";
    }

    @RequestMapping(value = "/confirmedDelete/{id}")
    public String confirmedDelete(@PathVariable Long id, @ModelAttribute("user") User user) {
        user.setId(id);
        userService.delete(id);
        return "redirect:list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @RequestMapping("/")
    public Principal user(Principal user) {
        return user;
    }
}
