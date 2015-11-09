package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Person;
import drocck.sp.beesandhoney.business.services.OwnerService;
import drocck.sp.beesandhoney.business.services.PersonService;
import drocck.sp.beesandhoney.business.services.YardService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Oscar on 9/27/2015.
 */

@Controller
@RequestMapping("owner")
public class OwnerController {
    private static final Log logger = LogFactory.getLog(YardController.class);

    @Autowired
    private PersonService personService;
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private YardService yardService;

    @ModelAttribute("allOwners")
    public @ResponseBody
    List<Person> populateOwners(){
        return ownerService.findAll();
    }

    @ModelAttribute("owner")
    public Person createModel() {
        return new Person();
    }

    /** Request Mapping **/

    @RequestMapping(value ="/list",  method = RequestMethod.GET)
    public String list() {
        return "owner/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "owner/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Person owner) {
        personService.save(owner);
        return "redirect:owner/list";
    }
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(Model model, @RequestParam("id") Long id) {
        model.addAttribute("owner", personService.findById(id));
        model.addAttribute("yard", yardService.findById(id));
        return "owner/read";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, @RequestParam("id") Long id) {
        model.addAttribute("owner", personService.findById(id));
        return "owner/update";
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Person owner) {
        personService.update(owner);
        return "redirect:owner/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Model model, @RequestParam("id") Long id) {
        model.addAttribute("owner", personService.findById(id));
        return "owner/delete";
    }
    @RequestMapping(value = "/confirmedDelete")
    public String deleteConfirmed(@RequestParam("id") Long id){
        personService.delete(id);
        return "redirect:owner/list";
    }
}
