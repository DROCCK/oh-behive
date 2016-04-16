package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Address;
import drocck.sp.beesandhoney.business.entities.ContactInfo;
import drocck.sp.beesandhoney.business.entities.Person;
import drocck.sp.beesandhoney.business.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;

/**
 * @author Robert Wilk
 *         Created on 9/30/2015.
 */
@Controller
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @ModelAttribute("person")
    public Person construct() {
        return new Person();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "person/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute Person person) {
        personService.save(person);
        return "redirect:list.html";
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable Long id, Model model) {
        model.addAttribute(personService.findOne(id));
        return "person/read";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("person", personService.findOne(id));
        return "person/update";
    }

    @RequestMapping(value = "/updatePerson/{id}", method = RequestMethod.POST)
    public String update(@PathVariable Long id, @ModelAttribute("person") Person person) {
        person.setId(id);
        personService.update(person);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("person", personService.findOne(id));
        return "person/delete";
    }

    @RequestMapping(value = "/confirmedDelete/{id}")
    public String confirmedDelete(@PathVariable Long id) {
        personService.delete(id);
        return "redirect:list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("people", personService.findAll());
        return "person/list";
    }
}
