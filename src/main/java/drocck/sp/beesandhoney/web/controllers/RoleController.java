package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Role;
import drocck.sp.beesandhoney.business.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Robert Wilk
 *         Created on 10/10/2015.
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ModelAttribute("role")
    public Role construct() {
        return new Role();
    }

    @RequestMapping(value = "/role/create", method = RequestMethod.GET)
    public String create() {
        return "role/create";
    }

    @RequestMapping(value = "/role/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("role") Role role) {
        roleService.save(role);
        return "redirect:/role/list";
    }

    @RequestMapping(value = "/role/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable Long id, Model model) {
        model.addAttribute("role", roleService.findById(id));
        return "role/read";
    }

    @RequestMapping(value = "/role/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("role", roleService.findById(id));
        return "role/update";
    }

    @RequestMapping(value = "/role/updateRole/{id}", method = RequestMethod.POST)
    public String updateRole(@PathVariable Long id, @ModelAttribute("role") Role role) {
        role.setId(id);
        roleService.save(role);
        return "redirect:/role/list";
    }

    @RequestMapping(value = "/role/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("role", roleService.findById(id));
        return "role/delete";
    }

    public String confirmedDelete(Long id) {
        roleService.delete(id);
        return "redirect:/role/list";
    }

    @RequestMapping(value = "/role/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "role/list";
    }
}
