package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Employee;
import drocck.sp.beesandhoney.business.services.EmployeeService;
import drocck.sp.beesandhoney.business.entities.Person;
import drocck.sp.beesandhoney.business.services.PersonService;
import drocck.sp.beesandhoney.business.entities.User;
import drocck.sp.beesandhoney.business.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.List;
/**
 * Created by Kyle on 10/10/2015.
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PersonService personService;

    @Autowired
    private UserService userService;

    @ModelAttribute("allEmployees")
    public List<Employee> populateEmployees() {
        return employeeService.findAll();
    }

    @ModelAttribute("allUsers")
    public Collection<User> populateUsers() {
        return userService.findAll();
    }


    @ModelAttribute("employee")
    public Employee construct() {
        return new Employee();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "employee/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute Employee employee) {
        employeeService.save(employee);
        return "redirect:list.html";
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable Long id, Model model) {
        model.addAttribute(employeeService.findOne(id));
        return "employee/read";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.findOne(id));
        return "employee/update";
    }

    @RequestMapping(value = "/updateEmployee/{id}", method = RequestMethod.POST)
    public String update(Employee employee) {
        employeeService.update(employee);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.findOne(id));
        return "employee/delete";
    }

    @RequestMapping(value = "/confirmedDelete/{id}")
    public String confirmedDelete(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("people", employeeService.findAll());
        return "employee/list";
    }
}
