package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Address;
import drocck.sp.beesandhoney.business.entities.ContactInfo;
import drocck.sp.beesandhoney.business.entities.Person;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.services.AddressService;
import drocck.sp.beesandhoney.business.services.ContactInfoService;
import drocck.sp.beesandhoney.business.services.PersonService;
import drocck.sp.beesandhoney.business.services.YardService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Connor on 9/26/2015.
 */
@Controller
@RequestMapping("yard")
public class YardController {
    private static final Log logger = LogFactory.getLog(YardController.class);

    @Autowired
    private YardService yardService;

    @Autowired
    private PersonService personService;

    @Autowired
    private AddressService addressService;

    /**
     * Models
     **/

    @ModelAttribute("allYards")
    public List<Yard> populateYards() {
        List<Yard> allYards = yardService.findAll();
        return allYards;
    }

    @ModelAttribute("allPeople")
    public List<Person> populatePeople() {
        List<Person> allPeople = personService.findAll();
        return allPeople;
    }

    @ModelAttribute("yard")
    public Yard createModel() {
        return new Yard();
    }

    /**
     * Request Mapping
     **/

    @RequestMapping("/json")
    @ResponseBody
    public List<Yard> json() {
        List<Yard> yardList = yardService.findAll();
        Hibernate.initialize(yardList);
        List<Address> addressList = addressService.findAll();
        Hibernate.initialize(addressList);
        List<Person> personList = personService.findAll();
        Hibernate.initialize(personList);
        return yardList;
    }

    @RequestMapping({"/list"})
    public String list() {
        return "yard/list";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable Long id) {
        model.addAttribute("yard", yardService.findById(id));
        return "yard/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Yard yard) {
        yardService.save(yard);
        return "redirect:yard/list";
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public String read(Model model, @PathVariable Long id) {
        model.addAttribute("yard", yardService.findById(id));
        return "yard/read";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable Long id) {
        model.addAttribute("yard", yardService.findById(id));
        return "yard/delete";
    }

    @RequestMapping(value = "/confirmedDelete/{id}", method = RequestMethod.GET)
    public String confirmedDelete(@PathVariable Long id) {
        ArrayList<Yard> toBeDeleted = new ArrayList<>();
        toBeDeleted.add(yardService.findById(id));
        yardService.deleteInBatch(toBeDeleted);
        return "redirect:yard/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "yard/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Yard yard) {
        yardService.save(yard);
        return "redirect:yard/list";
    }
}
