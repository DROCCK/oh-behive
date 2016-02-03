package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Region;
import drocck.sp.beesandhoney.business.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Kyle on 11/21/2015.
 */
@Controller
@RequestMapping("region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @ModelAttribute("region")
    public Region construct() {
        return new Region();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "region/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("region") Region region) {
        regionService.save(region);
        return "redirect:list";
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable Long id, Model model) {
        model.addAttribute("region", regionService.findOne(id));
        return "region/read";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("region", regionService.findOne(id));
        return "region/update";
    }

    @RequestMapping(value = "/updateRegion/{id}", method = RequestMethod.POST)
    public String updateRegion(@PathVariable Long id, Region region) {
        regionService.save(region);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("region", regionService.findOne(id));
        return "region/delete";
    }

    @RequestMapping(value = "/confirmedDelete/{id}")
    public String confirmedDelete(@PathVariable Long id) {
        regionService.delete(id);
        return "redirect:list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("regions", regionService.findAll());
        return "region/list";
    }
}
