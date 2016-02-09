package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Inspection;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.services.InspectionService;
import drocck.sp.beesandhoney.business.services.YardService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Connor
 * on 10/9/2015.
 */
@Controller
@RequestMapping("inspection")
public class InspectionController {
    private static final Log logger = LogFactory.getLog(InspectionController.class);

    @Autowired
    private InspectionService inspectionService;

    @Autowired
    private YardService yardService;

    // Models

    @ModelAttribute("allYards")
    public List<Yard> populateYards() {
        return yardService.findAll();
    }

    @ModelAttribute("inspection")
    public Inspection createInspectionModel() {
        return new Inspection();
    }

    @ModelAttribute("inspections")
    public ArrayList<Inspection> createInspectionsModel() {
        return new ArrayList<Inspection>();
    }

    // Request Mapping
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public String list(Model model, @PathVariable Long id) {
        // The drop site being displayed
        Yard yard = yardService.findOne(id);
        model.addAttribute("yard", yard);
        model.addAttribute("inspections", inspectionService.findAllByYard(yard));
        return "inspection/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("inspections", inspectionService.findAll());
        return "inspection/list";
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public String read(Model model, @PathVariable Long id) {
        model.addAttribute("inspection", inspectionService.findOne(id));
        return "inspection/read";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable Long id) {
        model.addAttribute("inspection", inspectionService.findOne(id));
        return "inspection/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Inspection inspection,
                         @RequestParam(value = "fedChecked", required = false) boolean fedChecked) {
        long redirect = inspection.getYard().getId();
        inspection.setIsFed(fedChecked);
        inspectionService.save(inspection);
        return "redirect:list/" + redirect;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable Long id) {
        model.addAttribute("inspection", inspectionService.findOne(id));
        return "inspection/delete";
    }

    @RequestMapping(value = "/confirmedDelete/{id}", method = RequestMethod.GET)
    public String confirmedDelete(@PathVariable Long id) {
        Inspection i = inspectionService.findOne(id);
        long redirect = i.getYard().getId();
        inspectionService.delete(i);
        return "redirect:list/" + redirect;
    }

    @RequestMapping(value = "/create")
    public String create() {
        return "inspection/create";
    }

    @RequestMapping(value = "create/{yardId}", method = RequestMethod.GET)
    public String create(@PathVariable Long yardId, Model model) {
        model.addAttribute("yard", yardService.findOne(yardId));
        return "inspection/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Inspection inspection,
                         @RequestParam(value = "fedChecked", required = false) boolean fedChecked,
                         @RequestParam(value = "yardId", required = true) long yardId) {
        inspection.setYard(yardService.findOne(yardId));
        inspection.setIsFed(fedChecked);
        inspectionService.save(inspection);
        long redirect = inspection.getYard().getId();
        return "redirect:list/" + redirect;
    }
}
