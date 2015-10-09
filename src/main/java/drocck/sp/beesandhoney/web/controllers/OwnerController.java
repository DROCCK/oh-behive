package drocck.sp.beesandhoney.web.controllers;

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

    @Autowired
    public OwnerController(OwnerService newOwnerService){
        super();
        ownerService = newOwnerService;
    }

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

    @RequestMapping({"/" ,"/index"})
    public String index() {
        return "/owner/index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/owner/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Person owner) {
        ownerService.save(owner);
        return "redirect:/owner/";
    }
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(Model model, @RequestParam("id") Long id) {
        model.addAttribute("owner", ownerService.findById(id));
        model.addAttribute("yard", yardService.findById(ownerService.findById(id).getId()));
        return "/owner/read";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, @RequestParam("id") Long id) {
        model.addAttribute("owner", ownerService.findById(id));
        return "/owner/update";
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Person owner) {
        ownerService.save(owner);
        return "redirect:/owner/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Model model, @RequestParam("id") Long id) {
        model.addAttribute("owner", ownerService.findById(id));
        return "/owner/delete";
    }
    @RequestMapping(value = "/confirmedDelete")
    public String deleteConfirmed(@RequestParam("id") Long id){
        ownerService.delete(id);
        return "redirect:/owner/";
    }
    /* currently not in use
   @RequestMapping(value="/", method = RequestMethod.POST)
    public String ownerSubmit(@ModelAttribute Owner newOwner, Model newModel) {
        newModel.addAttribute("owner", newOwner);
        return index();
    }
    */
}
