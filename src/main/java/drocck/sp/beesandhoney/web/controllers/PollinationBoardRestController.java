package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.*;
import drocck.sp.beesandhoney.business.entities.DTOs.ContractDTO;
import drocck.sp.beesandhoney.business.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Robert Wilk
 *         Created on 2/2/2016.
 */
@RestController
public class PollinationBoardRestController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private PersonService personService;

    @Autowired
    private OrchardService orchardService;

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private InspectionService inspectionService;

    @Autowired
    private YardService yardService;

    @ModelAttribute("shipment")
    public Shipment constructShipment() {
        return new Shipment();
    }

    @ModelAttribute("contract")
    public Contract constructContract() {
        return new Contract();
    }

    @ModelAttribute("inspection")
    public Inspection constructInspection() {
        return new Inspection();
    }

    @RequestMapping(value = "pollination/contracts", method = RequestMethod.GET)
    public List<ContractDTO> contracts() {
        return getSampleContracts();
        // return contractService.findAllAsDTO();
    }

    @RequestMapping(value = "pollination/contract/{id}", method = RequestMethod.GET)
    public Contract contract(@PathVariable("id") Long id) {
        ContractDTO cDto = getSampleContracts().get(id.intValue());
        Contract c = new Contract();
        /*Orchard o = new Orchard();
        Yard y = yardService.findAll().get(0);
        o.setYardName("Orchard" + id);
        o.setId(y.getId());
        o.setAddress(y.getAddress());
        o.setMaxHives(y.getMaxHives());
        o.setStatus(y.getStatus());
        o.setLongitude(y.getLongitude());
        o.setLatitude(y.getLatitude());*/
        c.setId(cDto.getId());
        //c.setOrchard(o);
        c.setBroker(personService.findAll().get(0));
        c.setAmount(100);
        c.setMoveInDate(new Date(23000));
        c.setMoveOutDate(new Date(35000));
        return c;
        //return contractService.findOne(id);
    }

    @RequestMapping(value = "pollination/addContract", method = RequestMethod.POST)
    public void addContract(@ModelAttribute("contract") Contract contract) {
        contractService.save(contract);
    }

    @RequestMapping(value = "pollination/contract/update", method = RequestMethod.POST)
    public void update(@ModelAttribute("contract") Contract contract) {
        contractService.save(contract);
    }

    @RequestMapping(value = "pollination/contractShipments/{id}", method = RequestMethod.GET)
    public List<Shipment> getShipment(@PathVariable("id") Long id) {
        return shipmentService.findAllByYard(orchardService.findOne(id));
    }

    @RequestMapping(value = "pollination/addShipment", method = RequestMethod.POST)
    public void addShipment(@ModelAttribute("shipment") Shipment shipment) {
        shipmentService.save(shipment);
    }

    @RequestMapping(value = "pollination/contacts/{id}", method = RequestMethod.GET)
    public List<Person> contacts(@PathVariable("id") Long id) {
        // return orchardService.findOne(id).getContacts();
        return personService.findAll();
    }

    @RequestMapping(value = "pollination/inspections/{id}")
    public List<Inspection> inspections(@PathVariable("id") Long id) {
        return inspectionService.findAllByYard(orchardService.findOne(id));
    }

    @RequestMapping(value = "pollination/addInspection")
    public void addInspection(@ModelAttribute("inspection") Inspection inspection) {
        inspectionService.save(inspection);
    }

    @RequestMapping(value = "pollination/inspection/{id}")
    public Inspection getInspection(@PathVariable("id") Long id) {
        return inspectionService.findOne(id);
    }

    protected static List<ContractDTO> getSampleContracts() {
        List<ContractDTO> contracts = new ArrayList<>();
        Random r = new Random((int) Math.random());
        for (long i = 0; i < 6; i++) {
            ContractDTO c = new ContractDTO();
            c.setId(i);
            c.setOrchardName("Orchard" + i);
            c.setProgress(i * r.nextDouble() * 10 * Math.sqrt(2.0) + 5.0);
            contracts.add(c);
        }
        return contracts;
    }
}
