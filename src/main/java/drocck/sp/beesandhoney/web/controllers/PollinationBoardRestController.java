package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.*;
import drocck.sp.beesandhoney.business.entities.DTOs.ContractCreateDTO;
import drocck.sp.beesandhoney.business.entities.DTOs.ContractDTO;
import drocck.sp.beesandhoney.business.entities.DTOs.OrchardCreateDTO;
import drocck.sp.beesandhoney.business.entities.DTOs.PolliShipmentCreateDto;
import drocck.sp.beesandhoney.business.services.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Policy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private PolliShipmentService polliShipmentService;

    @Autowired
    private InspectionService inspectionService;

    @Autowired
    private RegionService regionService;

    @ModelAttribute("orchard")
    public Orchard constructOrchard() {
        return new Orchard();
    }

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
        return contractService.findAllAsDTO();
    }

    @RequestMapping(value = "pollination/contract/{id}", method = RequestMethod.GET)
    public Contract contract(@PathVariable("id") Long id) {
        return contractService.findOne(id);
    }

    @RequestMapping(value = "pollination/createContract", method = RequestMethod.GET)
    public ContractCreateDTO createContract() {
        ContractCreateDTO c = new ContractCreateDTO();
        c.setPeople(personService.findAll());
        c.setOrchards(orchardService.findAllOrchardNames());
        return c;
    }

    @RequestMapping(value = "pollination/createOrchard")
    public OrchardCreateDTO createOrchard() {
        OrchardCreateDTO ocdto = new OrchardCreateDTO();
        ocdto.setStati(Yard.getStati());
        ocdto.setPeople(personService.findAll());
        ocdto.setRegions(regionService.findAllRegionNames());
        return ocdto;
    }

    @RequestMapping(value = "pollination/createShipment")
    public PolliShipmentCreateDto createShipment() {
        List<String> list = new ArrayList<>();
        for (PolliShipment.Direction direction : PolliShipment.Direction.values())
            list.add(direction.name());
        PolliShipmentCreateDto pscd = new PolliShipmentCreateDto();
        pscd.setDirections(list);
        return pscd;
    }

    @RequestMapping(value = "pollination/addOrchard/{json}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Orchard addOrchard(@PathVariable("json") String json) {
        return orchardService.save(new JSONObject(json));
    }

    @RequestMapping(value = "pollination/addContract/{json}", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public Contract addContract(@PathVariable("json") String json) {
        return contractService.save(new JSONObject(json));
    }

    @RequestMapping(value = "pollination/addShipment/{json}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PolliShipment addShipment(@PathVariable("json") String json) {
        return polliShipmentService.save(new JSONObject(json));
    }

    @RequestMapping(value = "pollination/orchards", method = RequestMethod.GET)
    public List<Orchard> orchards() {
        return orchardService.findAll();
    }

    @RequestMapping(value = "pollination/fullContracts", method = RequestMethod.GET)
    public List<Contract> fullContracts() { return contractService.findAll(); }

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
        return null; // shipmentService.findAllByYard(orchardService.findOne(id));
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
        return null; //inspectionService.findAllByYard(orchardService.findOne(id));
    }

    @RequestMapping(value = "pollination/addInspection")
    public void addInspection(@ModelAttribute("inspection") Inspection inspection) {
        inspectionService.save(inspection);
    }

    @RequestMapping(value = "pollination/inspection/{id}")
    public Inspection getInspection(@PathVariable("id") Long id) {
        return inspectionService.findOne(id);
    }
}
