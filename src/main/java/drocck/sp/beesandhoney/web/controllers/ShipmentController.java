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

import java.util.Calendar;
import java.util.Date;
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
        List<Shipment> allShipments = shipmentService.findAll();
        return allShipments;
    }

    @ModelAttribute("allYards")
    public List<Yard> createYardList() { return yardService.findAll(); }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "shipment/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Shipment shipment) {
        shipment.setStatus(shipment.getStatusInactive());
        shipment.setFromYard(yardService.findOne(shipment.getFromYardID()));
        shipment.setToYard(yardService.findOne(shipment.getToYardID()));

        shipment.decrementMaxHives();
        shipmentService.save(shipment);
        return "redirect:shipment/list";
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
        shipment.setFromYard(yardService.findOne(shipment.getFromYardID()));
        shipment.setToYard(yardService.findOne(shipment.getToYardID()));
        //System.out.println("status = " + shipment.getStatus() );
        if(shipment.getStatus().equals(shipment.getStatusComplete()) ){
            //System.out.println("status is equal to completed!");
            Date temp = new Date(Calendar.getInstance().getTime().getTime());
            shipment.setArrivalDate(temp);
            shipment.incrementMaxHives();
        }
        shipmentService.save(shipment);
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
        return "redirect:shipment/list";
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
