package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Person;
import drocck.sp.beesandhoney.business.services.OwnerService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    /* currently not in use
   @RequestMapping(value="/", method = RequestMethod.POST)
    public String ownerSubmit(@ModelAttribute Owner newOwner, Model newModel) {
        newModel.addAttribute("owner", newOwner);
        return index();
    }
    */
}
