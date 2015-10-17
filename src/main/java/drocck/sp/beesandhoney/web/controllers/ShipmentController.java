package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Shipment;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.services.ShipmentService;
import drocck.sp.beesandhoney.business.services.YardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 10/17/2015.
 */
@Controller
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private YardService yardService;

    @ModelAttribute("shipment")
    public Shipment construct() {
        return new Shipment();
    }

    @ModelAttribute("allShipments")
    public List<Shipment> createShipmentList() { return shipmentService.findAll(); }

    @ModelAttribute("allYards")
    public List<Yard> createYardList() { return yardService.findAll(); }

    @RequestMapping(value = "/shipment/create", method = RequestMethod.GET)
    public String create() {
        return "shipment/create";
    }

    @RequestMapping(value = "/shipment/create", method = RequestMethod.POST)
    public String create(Shipment shipment) {
        shipmentService.save(shipment);
        return "redirect:/shipment/list";
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
    public String update(@PathVariable Long id, Shipment shipment) {
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
        List<Shipment> allShipments = shipmentService.findAll();
        model.addAttribute("allShipments", allShipments);
        for(Shipment s : allShipments)
            System.out.println(s.getDoubleHive());
        return "shipment/list";
    }
}
