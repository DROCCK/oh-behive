package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Person;
import drocck.sp.beesandhoney.business.entities.repositories.PersonRepository;
import drocck.sp.beesandhoney.business.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Rob
 *         Created on 9/30/2015.
 */
@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @ModelAttribute("person")
    public Person construct() {
        return new Person();
    }

    @RequestMapping(value = "/person/create", method = RequestMethod.GET)
    public String create() {
        return "person/create";
    }

    @RequestMapping(value = "/person/create", method = RequestMethod.POST)
    public String create(@ModelAttribute Person person) {
        personService.save(person);
        return "redirect:/person/list.html";
    }

    @RequestMapping(value = "/person/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("people", personRepository.findAll());
        return "person/list";
    }
}
