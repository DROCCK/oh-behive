package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Owner;
import drocck.sp.beesandhoney.business.entities.Person;
import drocck.sp.beesandhoney.business.services.OwnerService;
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
@RequestMapping("/owner")
public class OwnerController {
    private static final Log logger = LogFactory.getLog(YardController.class);

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private YardService yardService;

    @ModelAttribute("allOwners")
    @ResponseBody
    public List<Owner> populateOwners(){
        return ownerService.findAll();
    }

    @ModelAttribute("owner")
    public Owner createModel() {
        return new Owner();
    }

    @RequestMapping(value ="/list",  method = RequestMethod.GET)
    public String list() {
        return "/owner/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/owner/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute(value = "owner") Owner owner) {
        ownerService.save(owner);
        return "redirect:/owner/list";
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public String read(Model model, @PathVariable(value = "id") Long id) {
        Owner owner = ownerService.findOne(id);
        owner.setYards(yardService.findAllByOwner(owner));
        model.addAttribute("owner", owner);
        return "/owner/read";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable("id") Long id) {
        model.addAttribute("owner", ownerService.findOne(id));
        return "/owner/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Owner owner) {
        ownerService.update(owner);
        return "redirect:/owner/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable("id") Long id) {
        model.addAttribute("owner", ownerService.findOne(id));
        return "/owner/delete";
    }
    @RequestMapping(value = "/confirmedDelete/{id}")
    public String deleteConfirmed(@PathVariable("id") Long id){
        ownerService.delete(id);
        return "redirect:/owner/list";
    }
}
