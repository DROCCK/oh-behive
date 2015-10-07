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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Connor on 9/26/2015.
 */
@Controller
@RequestMapping("/yard")
public class YardController {
    private static final Log logger = LogFactory.getLog(YardController.class);

    @Autowired
    private YardService yardService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ContactInfoService contactInfoService;

    @Autowired
    private AddressService addressService;

    /**
     * Models
     **/

    @ModelAttribute("allYards")
    public List<Yard> populateYards() {
        List<Yard> allYards = yardService.findAll();
//        Iterator<Yard> itr = allYards.iterator();
//        while (itr.hasNext()) {
//            logger.info(itr.next().getOwnerName());
//        }
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

    @RequestMapping({"/", "/index", "/list"})
    public String index() {
        return "/yard/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, @RequestParam("id") Long id) {
        model.addAttribute("yard", yardService.findById(id));
        return "/yard/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Yard yard) {
        yardService.save(yard);
        return "redirect:/yard/";
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(Model model, @RequestParam("id") Long id) {
        model.addAttribute("yard", yardService.findById(id));
        return "/yard/read";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Long id) {
        ArrayList<Yard> toBeDeleted = new ArrayList<>();
        toBeDeleted.add(yardService.findById(id));
        yardService.deleteInBatch(toBeDeleted);
        return "redirect:/yard/";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/yard/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Yard yard) {
        yardService.save(yard);
        return "redirect:/yard/";
    }
}
