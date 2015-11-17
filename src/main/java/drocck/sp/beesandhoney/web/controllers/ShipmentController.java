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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 10/17/2015.
 */
@Controller
@RequestMapping("shipment")
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
    public List<Shipment> populateShipments() {
        return shipmentService.findAll();
    }

    @ModelAttribute("statusNames")
    public List<String> statusNames() {
        return Shipment.getStatusNames();
    }

    @ModelAttribute("allYards")
    public List<Yard> createYardList() { return yardService.findAll(); }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "shipment/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Shipment shipment) {
        shipmentService.save(shipment);
        return "redirect:list";
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable Long id, Model model) {
        model.addAttribute("shipment", shipmentService.findOne(id));
        return "shipment/read";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
     public String update(@PathVariable Long id, Model model) {
        model.addAttribute("shipment", shipmentService.findOne(id));
        return "shipment/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Shipment shipment) {
        shipmentService.update(shipment);
        return "shipment/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("shipment", shipmentService.findOne(id));
        return "shipment/delete";
    }

    @RequestMapping(value = "/confirmedDelete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        shipmentService.delete(id);
        return "redirect:list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Shipment> allShipments = shipmentService.findAll();
        model.addAttribute("allShipments", allShipments);
        return "shipment/list";
    }

    @RequestMapping("/list/{yardId}")
    public String listByYard(@PathVariable Long yardId, Model model) {
        model.addAttribute("allShipments", shipmentService.findAllByYard(yardService.findOne(yardId)));
        return "shipment/list";
    }
}
