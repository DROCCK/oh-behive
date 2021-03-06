package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Company;
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
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @ModelAttribute("company")
    public Company construct() {
        return new Company();
    }

    @ModelAttribute("allCompanies")
    @ResponseBody
    public List<Company> populateCompanies(){
        return companyService.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "company/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("company") Company company) {
        companyService.save(company);
        return "redirect:list";
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable Long id, Model model) {
        model.addAttribute("company", companyService.findOne(id));
        return "company/read";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("company", companyService.findOne(id));
        return "company/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable Long id, @ModelAttribute("company") Company company) {
        companyService.update(company);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("company", companyService.findOne(id));
        return "company/delete";
    }

    @RequestMapping("/confirmedDelete/{id}")
    public String confirmedDelete(@PathVariable Long id) {
        companyService.delete(id);
        return "redirect:list";
    }

    @RequestMapping(value ="/list",  method = RequestMethod.GET)
    public String list() {
        return "company/list";
    }
}