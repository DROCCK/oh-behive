package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.DTOs.YardCreateDTO;
import drocck.sp.beesandhoney.business.entities.Inspection;
import drocck.sp.beesandhoney.business.entities.Region;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.services.InspectionService;
import drocck.sp.beesandhoney.business.services.PersonService;
import drocck.sp.beesandhoney.business.services.RegionService;
import drocck.sp.beesandhoney.business.services.YardService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by David on 3/8/2016.
 */
@RestController
public class BeeBoardRestController {

    @Autowired
    private PersonService personService;

    @Autowired
    private InspectionService inspectionService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private YardService yardService;

    @ModelAttribute("inspection")
    public Inspection constructInspection() {
        return new Inspection();
    }

    @RequestMapping(value="dashboard/beeboard/json", method = RequestMethod.GET)
    public List<Region> json() {
        return regionService.findAll();
    }

    @RequestMapping(value = "beeboard/createYard")
    public YardCreateDTO createYard() {
        YardCreateDTO ycdto = new YardCreateDTO();
        ycdto.setStati(Yard.getStati());
        ycdto.setPeople(personService.findAll());
        ycdto.setRegions(regionService.findAllRegionNames());
        return ycdto;
    }

    @RequestMapping(value = "beeboard/addYard/{json}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Yard addYard(@PathVariable("json") String json) {
        return yardService.save(new JSONObject(json));
    }
}
