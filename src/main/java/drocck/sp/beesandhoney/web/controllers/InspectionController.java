package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Inspection;
import drocck.sp.beesandhoney.business.services.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

/**
 * Created by cjeli_000 on 10/9/2015.
 */
@Controller
@RequestMapping("/inspection")
public class InspectionController {
    @Autowired
    private InspectionService inspectionService;

    /**
     * Models
     **/

    @ModelAttribute("inspection")
    public Inspection createInspectionModel() {return new Inspection(); }

    /**
     * Request Mapping
     **/

    @RequestMapping(name = "/list/{id}", method = RequestMethod.GET)
    public String list(Model model, @PathVariable Long id) {
        // need Dropsite Service
        //model.addAttribute("inspections", inspectionService.findByDropsite(id));
        return "/inspection/list";
    }

    @RequestMapping(name = "/read/{id}")
    public String read(Model model, @PathVariable Long id) {
        model.addAttribute("inspection", inspectionService.findById(id));
        return "/inspection/read";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable Long id) {
        model.addAttribute("inspection", inspectionService.findById(id));
        return "/inspection/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Inspection inspection) {
        inspectionService.save(inspection);
        return "redirect:/inspection/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable Long id) {
        model.addAttribute("inspection", inspectionService.findById(id));
        return "inspection/delete";
    }

    @RequestMapping(value = "/confirmedDelete/{id}", method = RequestMethod.GET)
    public String confirmedDelete(@PathVariable Long id) {
        inspectionService.delete(inspectionService.findById(id));
        return "redirect:/inspection/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/inspection/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Inspection inspection) {
        inspectionService.save(inspection);
        return "redirect:/inspection/list.html";
    }
}
