package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.*;
import drocck.sp.beesandhoney.business.services.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 10/10/2015.
 */
@Controller
@RequestMapping("/dropsite")
public class DropSiteController {
    private static final Log logger = LogFactory.getLog(DropSiteController.class);

    @Autowired
    private DropSiteService dropSiteService;
    /**
     * Models
     **/


    @ModelAttribute("allDropSites")
    public List<DropSite> populateDrops() {
        List<DropSite> allDropSites = dropSiteService.findAll();
        return allDropSites;
    }

    @ModelAttribute("dropSite")
    public DropSite createModel() {
        return new DropSite();
    }

    /**
     * Request Mapping
     **/

    @RequestMapping({"/list"})
    public String list() {
        return "/dropsite/list";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable Long id) {
        model.addAttribute("dropSite", dropSiteService.findById(id));
        return "/dropsite/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(DropSite dropSite) {
        dropSiteService.save(dropSite);
        return "redirect:/dropsite/list";
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public String read(Model model, @PathVariable Long id) {
        model.addAttribute("dropSite", dropSiteService.findById(id));
        return "/dropsite/read";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable Long id) {
        model.addAttribute("dropSite", dropSiteService.findById(id));
        return "/dropsite/delete";
    }

    @RequestMapping(value = "/confirmedDelete/{id}", method = RequestMethod.GET)
    public String confirmedDelete(@PathVariable Long id) {
        ArrayList<DropSite> toBeDeleted = new ArrayList<>();
        toBeDeleted.add(dropSiteService.findById(id));
        dropSiteService.deleteInBatch(toBeDeleted);
        return "redirect:/dropsite/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/dropsite/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(DropSite dropSite) {
        dropSiteService.save(dropSite);
        logger.info(dropSite.getLatitude());
        logger.info(dropSite.getLongitude());
        return "redirect:/dropsite/list.html";
    }
}
