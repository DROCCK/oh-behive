package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Owner;
import drocck.sp.beesandhoney.business.entities.Person;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.services.AddressService;
import drocck.sp.beesandhoney.business.services.OwnerService;
import drocck.sp.beesandhoney.business.services.PersonService;
import drocck.sp.beesandhoney.business.services.YardService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Connor on 9/26/2015.
 */
@Controller
@RequestMapping("yard")
public class YardController {
    private static final Log logger = LogFactory.getLog(YardController.class);
    private String referer;
    @Autowired
    private YardService yardService;

    @Autowired
    public OwnerService ownerService;

    @Autowired
    private PersonService personService;

    // Helper Methods

    /**
     * Returns the viewName to return for coming back to the sender url
     *
     * @param request Instance of {@link HttpServletRequest} or use an injected instance
     * @return Optional with the view name. Recommended to use an alternative url with
     * {@link Optional#orElse(java.lang.Object)}
     */
    protected Optional<String> getPreviousPageByRequest(HttpServletRequest request)
    {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }


    // Models
    @ModelAttribute("allYards")
    public List<Yard> populateYards() {
        return yardService.findAll();
    }

    @ModelAttribute("allOwners")
    public List<Owner> populateOwners() {
        return ownerService.findAll();
    }

    @ModelAttribute("allPeople")
    public List<Person> populatePeople() {
        return personService.findAll();
    }

    @ModelAttribute("yard")
    public Yard createModel() {
        return new Yard();
    }


    //Request Mapping

    @RequestMapping("/json")
    @ResponseBody
    public List<Yard> json() {
        return yardService.findAll();
    }

    @RequestMapping({"/list"})
    public String list() {
        return "yard/list";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable Long id, HttpServletRequest request) {
        // get how the user went to the update page
        referer = getPreviousPageByRequest(request).orElse("/");
        model.addAttribute("yard", yardService.findOne(id));
        return "yard/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Yard yard) {
        yardService.save(yard);
        // return user to where they were before updating
        return referer;
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public String read(Model model, @PathVariable Long id) {
        model.addAttribute("yard", yardService.findOne(id));
        return "yard/read";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable Long id, HttpServletRequest request) {
        // get how the user got to the delete page
        referer = getPreviousPageByRequest(request).orElse("/");
        model.addAttribute("yard", yardService.findOne(id));
        return "yard/delete";
    }

    @RequestMapping(value = "/confirmedDelete/{id}", method = RequestMethod.GET)
    public String confirmedDelete(@PathVariable Long id) {
        ArrayList<Yard> toBeDeleted = new ArrayList<>();
        toBeDeleted.add(yardService.findOne(id));
        yardService.deleteInBatch(toBeDeleted);
        // return user to where they were before entering delete page
        return referer;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(HttpServletRequest request) {
        // get how the user got to the create page
        referer = getPreviousPageByRequest(request).orElse("/");
        return "yard/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Yard yard) {
        yardService.save(yard);
        return referer;
    }
}
