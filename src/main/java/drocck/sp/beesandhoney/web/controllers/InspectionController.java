package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.DropSite;
import drocck.sp.beesandhoney.business.entities.Inspection;
import drocck.sp.beesandhoney.business.services.DropSiteService;
import drocck.sp.beesandhoney.business.services.InspectionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjeli_000 on 10/9/2015.
 */
@Controller
@RequestMapping("inspection")
public class InspectionController {
    private static final Log logger = LogFactory.getLog(InspectionController.class);

    @Autowired
    private InspectionService inspectionService;

    @Autowired
    private DropSiteService dropSiteService;
    /**
     * Models
     **/
    @ModelAttribute("allDropSites")
    public List<DropSite> populateDrops() {
        return dropSiteService.findAll();
    }

    @ModelAttribute("inspection")
    public Inspection createInspectionModel() {return new Inspection(); }

    @ModelAttribute("inspections")
    public ArrayList<Inspection> createInspectionsModel() {return new ArrayList<Inspection>(); }

    /**
     * Request Mapping
     **/

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public String list(Model model, @PathVariable Long id) {
        DropSite dropSite = dropSiteService.findById(id); // The drop site being displayed
        model.addAttribute("dropSite", dropSite);
        model.addAttribute("inspections", inspectionService.findByDropsite(dropSite));
        return "inspection/list";
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public String read(Model model, @PathVariable Long id) {
        model.addAttribute("inspection", inspectionService.findById(id));
        return "inspection/read";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable Long id) {
        model.addAttribute("inspection", inspectionService.findById(id));
        return "inspection/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Inspection inspection, @RequestParam(value = "fedChecked", required = false) boolean fedChecked) {
        long redirect = inspection.getDropSite().getId();
        inspection.setIsFed(fedChecked);
        inspectionService.save(inspection);
        return "redirect:inspection/list/" + redirect;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable Long id) {
        model.addAttribute("inspection", inspectionService.findById(id));
        return "inspection/delete";
    }

    @RequestMapping(value = "/confirmedDelete/{id}", method = RequestMethod.GET)
    public String confirmedDelete(@PathVariable Long id) {
        Inspection i = inspectionService.findById(id);
        long redirect = i.getDropSite().getId();
        inspectionService.delete(i);
        return "redirect:inspection/list/" + redirect;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "inspection/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Inspection inspection, @RequestParam(value = "fedChecked", required = false) boolean fedChecked) {
        long redirect = inspection.getDropSite().getId();
        inspection.setIsFed(fedChecked);
        inspectionService.save(inspection);
        return "redirect:inspection/list/" + redirect;
    }
}
