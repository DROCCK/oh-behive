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
@RequestMapping("/drop")
public class DropController {
    private static final Log logger = LogFactory.getLog(DropController.class);

    @Autowired
    private DropService dropService;
    /**
     * Models
     **/


    @ModelAttribute("allDrops")
    public List<Drop> populateDrops() {
        List<Drop> allDrops = dropService.findAll();
        return allDrops;
    }

    @ModelAttribute("drop")
    public Drop createModel() {
        return new Drop();
    }

    /**
     * Request Mapping
     **/

    @RequestMapping({"/list"})
    public String list() {
        return "/drop/list";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable Long id) {
        model.addAttribute("drop", dropService.findById(id));
        return "/drop/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Drop drop) {
        dropService.save(drop);
        return "redirect:/drop/list";
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public String read(Model model, @PathVariable Long id) {
        model.addAttribute("drop", dropService.findById(id));
        return "/drop/read";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable Long id) {
        model.addAttribute("drop", dropService.findById(id));
        return "/drop/delete";
    }

    @RequestMapping(value = "/confirmedDelete/{id}", method = RequestMethod.GET)
    public String confirmedDelete(@PathVariable Long id) {
        ArrayList<Drop> toBeDeleted = new ArrayList<>();
        toBeDeleted.add(dropService.findById(id));
        dropService.deleteInBatch(toBeDeleted);
        return "redirect:/drop/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/drop/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Drop drop) {
        dropService.save(drop);
        return "redirect:/drop/list.html";
    }
}
