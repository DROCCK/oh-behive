package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.*;
import drocck.sp.beesandhoney.business.entities.DTOs.*;
import drocck.sp.beesandhoney.business.services.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private PolliInspectionService inspectionService;

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
        return getContractCreateDTO();
    }

    @RequestMapping(value = "pollination/editContract/{id}", method = RequestMethod.GET)
    public ContractEditDTO editContract(@PathVariable("id") Long id) {
        return new ContractEditDTO(getContractCreateDTO(), contractService.findOne(id));
    }

    @RequestMapping(value = "pollination/orchard/{id}", method = RequestMethod.GET)
    public Orchard orchard(@PathVariable("id") Long id) {
        return orchardService.findOne(id);
    }

    @RequestMapping(value = "pollination/createOrchard")
    public OrchardCreateDTO createOrchard() {
        return getOrchardCreateDTO();
    }

    @RequestMapping(value = "pollination/editOrchard/{id}", method = RequestMethod.GET)
    public OrchardEditDTO editOrchard(@PathVariable("id") Long id) {
        return new OrchardEditDTO(getOrchardCreateDTO(), orchardService.findOne(id));
    }

    @RequestMapping(value = "pollination/editContractOrchard/{id}", method = RequestMethod.GET)
    public OrchardEditDTO editContractOrchard(@PathVariable("id") Long id) {
        return new OrchardEditDTO(getOrchardCreateDTO(), contractService.findOne(id).getOrchard());
    }

    @RequestMapping(value = "pollination/shipment/{id}", method = RequestMethod.GET)
    public PolliShipment shipment(@PathVariable("id") Long id) {
        return polliShipmentService.findOne(id);
    }

    @RequestMapping(value = "pollination/createShipment")
    public PolliShipmentCreateDTO createShipment() {
        return getPolliShipmentCreateDTO();
    }

    @RequestMapping(value = "pollination/editShipment/{id}", method = RequestMethod.GET)
    public PolliShipmentEditDTO editShipment(@PathVariable("id") Long id) {
        return new PolliShipmentEditDTO(getPolliShipmentCreateDTO(), polliShipmentService.findOne(id));
    }

    @RequestMapping(value = "pollination/createInspection", method = RequestMethod.GET)
    public PolliInspectionCreateDTO createPolliInspection() {
        return getPolliInspectionCreateDTO();
    }

    @RequestMapping(value = "pollination/shipments", method = RequestMethod.GET)
    public List<PolliShipment> shipments() {
        return polliShipmentService.findAll();
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

    @RequestMapping(value = "pollination/addInspection/{json}", method = RequestMethod.POST)
    public PolliInspection addInspection(@PathVariable("json") String json) {
        return inspectionService.save(new JSONObject(json));
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

/*    @RequestMapping(value = "pollination/contractShipments/{id}", method = RequestMethod.GET)
    public List<Shipment> getShipment(@PathVariable("id") Long id) {
        return null; // shipmentService.findAllByYard(orchardService.findOne(id));
    }*/

    @RequestMapping(value = "pollination/addShipment", method = RequestMethod.POST)
    public void addShipment(@ModelAttribute("shipment") Shipment shipment) {
        shipmentService.save(shipment);
    }

/*
    @RequestMapping(value = "pollination/contacts/{id}", method = RequestMethod.GET)
    public List<Person> contacts(@PathVariable("id") Long id) {
        // return orchardService.findOne(id).getContacts();
        return personService.findAll();
    }
*/

    @RequestMapping(value = "pollination/inspections/{id}")
    public List<PolliInspection> inspections(@PathVariable("id") Long id) {
        return inspectionService.findAllByOrchard(orchardService.findOne(id));
    }

    @RequestMapping(value = "pollination/addInspection")
    public void addInspection(@ModelAttribute("inspection") PolliInspection inspection) {
        inspectionService.save(inspection);
    }

    @RequestMapping(value = "pollination/inspection/{id}")
    public PolliInspection getInspection(@PathVariable("id") Long id) {
        return inspectionService.findOne(id);
    }

    @RequestMapping(value = "pollination/progress", method = RequestMethod.GET)
    public ProgressDTO progress() {
        return new ProgressDTO(contractService.findAll());
    }

    @RequestMapping(value = "pollination/delCon/{id}", method = RequestMethod.GET)
    public void d(@PathVariable("id") Long id) {
        contractService.delete(id);
    }

    @RequestMapping(value = "pollination/emptyOrchard/{contractId}")
    public void emptyOrchard(@PathVariable("contractId") Long contractId) {
        setAndSaveOrchard(getOrchardByContract(contractService.findOne(contractId)), 0);
    }

    @RequestMapping(value = "pollination/fillOrchard/{contractId}")
    public void fillOrchard(@PathVariable("contractId") Long contractId) {
        Contract c = contractService.findOne(contractId);
        setAndSaveOrchard(getOrchardByContract(c), c.getAmount());
    }

    private Orchard getOrchardByContract(Contract contract) {
        if (contract == null) {
            return null;
        }
        return contract.getOrchard();
    }

    private void setAndSaveOrchard(Orchard orchard, int count) {
        if (orchard == null) {
            return;
        }
        orchard.setCount(count);
        orchardService.save(orchard);
    }

    private PolliShipmentCreateDTO getPolliShipmentCreateDTO() {
        List<String> list = new ArrayList<>();
        for (PolliShipment.Direction direction : PolliShipment.Direction.values())
            list.add(direction.name());
        PolliShipmentCreateDTO pscd = new PolliShipmentCreateDTO();
        pscd.setDirections(list);
        return pscd;
    }

    private OrchardCreateDTO getOrchardCreateDTO() {
        OrchardCreateDTO ocdto = new OrchardCreateDTO();
        ocdto.setStati(Yard.getStati());
        ocdto.setPeople(personService.findAll());
        ocdto.setRegions(regionService.findAllRegionNames());
        return ocdto;
    }

    private ContractCreateDTO getContractCreateDTO() {
        ContractCreateDTO c = new ContractCreateDTO();
        c.setPeople(personService.findAll());
        c.setOrchards(orchardService.findAllOrchardNames());
        return c;
    }

    private PolliInspectionCreateDTO getPolliInspectionCreateDTO() {
        PolliInspectionCreateDTO picdto = new PolliInspectionCreateDTO();
        picdto.setOrchards(orchardService.findAllOrchardNames());
        List<String> purposes = new ArrayList<>();
        for (PolliInspection.Purpose purpose : PolliInspection.Purpose.values())
            purposes.add(purpose.name());
        picdto.setPurposes(purposes);
        return picdto;
    }
}
