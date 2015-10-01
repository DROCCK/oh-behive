package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Owner;
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
        initData();
    }

    private void initData(){
        Owner o1 = new Owner();
        o1.setID(1);
        o1.setName("Billy Bob");
        o1.setPhoneNumber("915-222-4444");
        o1.setEmail("BillyBob@BillyTheBob.com");
        o1.setStreet("1235 Whoa RD");
        o1.setCity("Sacramento");
        o1.setState("CA");
        o1.setZip("94643");
        ownerService.save(o1);
    }

    @ModelAttribute("allOwners")
    public @ResponseBody
    List<Owner> populateOwners(){
        return ownerService.findAll();
    }

    @ModelAttribute("owner")
    public Owner createModel() {
        return new Owner();
    }

    /** Request Mapping **/

    @RequestMapping({"/" ,"/index"})
    public String index() {
        return "/owner/index";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String yardSubmit(@ModelAttribute Owner newOwner, Model newModel) {
        newModel.addAttribute("owner", newOwner);
        return index();
    }
}
