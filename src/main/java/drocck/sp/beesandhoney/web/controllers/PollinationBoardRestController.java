package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.ContactInfo;
import drocck.sp.beesandhoney.business.entities.Contract;
import drocck.sp.beesandhoney.business.entities.DTOs.ContractDTO;
import drocck.sp.beesandhoney.business.entities.Shipment;
import drocck.sp.beesandhoney.business.services.ContactInfoService;
import drocck.sp.beesandhoney.business.services.ContractService;
import drocck.sp.beesandhoney.business.services.OrchardService;
import drocck.sp.beesandhoney.business.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 2/2/2016.
 */
@RestController
public class PollinationBoardRestController {

    @Autowired
    ContractService contractService;

    @Autowired
    ContactInfoService contactInfoService;

    @Autowired
    OrchardService orchardService;

    @Autowired
    ShipmentService shipmentService;

    @RequestMapping(value = "pollination/contracts", method = RequestMethod.GET)
    public List<ContractDTO> contracts() {
        return contractService.findAllAsDTO();
    }

    @RequestMapping(value = "pollination/contract/{id}", method = RequestMethod.GET)
    public Contract contract(@PathVariable("id") Long id) {
        return contractService.findOne(id);
    }

    @RequestMapping(value = "pollination/contract/update", method = RequestMethod.POST)
    public void update(@ModelAttribute("contract") Contract contract) {
        contractService.save(contract);
    }

    @RequestMapping(value = "pollination/contractShipments/{id}", method = RequestMethod.GET)
    public List<Shipment> shipments(@PathVariable("id") Long id) {
        return shipmentService.findAllByYard(orchardService.findOne(id));
    }

    @RequestMapping(value = "pollination/contacts/{id}", method = RequestMethod.GET)
    public List<ContactInfo> contacts(@PathVariable("id") Long id) {
        // return orchardService.findOne(id).getContacts();
        return contactInfoService.findAll();
    }
}
