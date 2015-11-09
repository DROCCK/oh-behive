package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Company;
import drocck.sp.beesandhoney.business.entities.Person;
import drocck.sp.beesandhoney.business.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Chai
 *         Created on 10/9/2015.
 */
@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @ModelAttribute("company")
    public Company construct() {
        return new Company();
    }

    @ModelAttribute("allCompanies")
    public @ResponseBody
    List<Company> populateCompanies(){
        return companyService.findAll();
    }

    @RequestMapping(value = "company/create", method = RequestMethod.GET)
    public String create() {
        return "company/create";
    }

    @RequestMapping(value = "company/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("company") Company company) {
        companyService.save(company);
        return "redirect:company/list";
    }

    @RequestMapping(value = "company/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable Long id, Model model) {
        model.addAttribute("company", companyService.findById(id));
        return "company/read";
    }

    @RequestMapping(value = "company/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("company", companyService.findById(id));
        return "company/update";
    }

    @RequestMapping(value = "company/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable Long id, @ModelAttribute("company") Company company) {
        companyService.update(company);
        return "redirect:company/list";
    }

    @RequestMapping(value = "company/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("company", companyService.findById(id));
        return "company/delete";
    }

    @RequestMapping("company/confirmedDelete/{id}")
    public String confirmedDelete(@PathVariable Long id) {
        companyService.delete(id);
        return "redirect:company/list";
    }

    @RequestMapping(value ="company/list",  method = RequestMethod.GET)
    public String list() {
        return "company/list";
    }
}