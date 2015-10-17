package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Shipment;
import drocck.sp.beesandhoney.business.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Robert Wilk
 *         Created on 10/17/2015.
 */
@Controller
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @ModelAttribute("shipment")
    public Shipment construct() {
        return new Shipment();
    }

    @RequestMapping(value = "/shipment/create", method = RequestMethod.GET)
    public String create() {
        return "shipment/create";
    }

    @RequestMapping(value = "/shipment/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("shipment") Shipment shipment) {
        shipmentService.save(shipment);
        return "redirect:/shimpment/list";
    }

    @RequestMapping(value = "/shipment/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable Long id, Model model) {
        model.addAttribute("shipment", shipmentService.findById(id));
        return "shipment/read";
    }

    @RequestMapping(value = "/shipment/update/{id}", method = RequestMethod.GET)
     public String update(@PathVariable Long id, Model model) {
        model.addAttribute("shipment", shipmentService.findById(id));
        return "shipment/update";
    }

    @RequestMapping(value = "/shipment/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable Long id, @ModelAttribute("shipment") Shipment shipment) {
        shipmentService.save(shipment);
        return "shipment/update";
    }

    @RequestMapping(value = "/shipment/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("shipment", shipmentService.findById(id));
        return "shipment/delete";
    }

    @RequestMapping(value = "/shipment/confirmedDelete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        shipmentService.delete(id);
        return "redirect:/shipment/delete";
    }

    @RequestMapping(value = "/shipment/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("shipment", shipmentService.findAll());
        return "shipment/list";
    }
}
