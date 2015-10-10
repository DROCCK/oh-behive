package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Employee;
import drocck.sp.beesandhoney.business.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Kyle on 10/10/2015.
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ModelAttribute("employee")
    public Employee construct() {
        return new Employee();
    }

    @RequestMapping(value = "/employee/create", method = RequestMethod.GET)
    public String create() {
        return "employee/create";
    }

    @RequestMapping(value = "/employee/create", method = RequestMethod.POST)
    public String create(@ModelAttribute Employee employee) {
        employeeService.save(employee);
        return "redirect:/employee/list.html";
    }

    @RequestMapping(value = "/employee/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable Long id, Model model) {
        model.addAttribute(employeeService.findById(id));
        return "employee/read";
    }

    @RequestMapping(value = "/employee/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "/employee/update";
    }

    @RequestMapping(value = "employee/updateEmployee/{id}", method = RequestMethod.POST)
    public String update(@PathVariable Long id, @ModelAttribute("employee") Employee employee) {
        employee.setId(id);
        employeeService.update(employee);
        return "redirect:/employee/list";
    }

    @RequestMapping(value = "/employee/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "/employee/delete";
    }

    @RequestMapping(value = "/employee/confirmedDelete/{id}")
    public String confirmedDelete(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:/employee/list";
    }

    @RequestMapping(value = "/employee/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("people", employeeService.findAll());
        return "employee/list";
    }
}