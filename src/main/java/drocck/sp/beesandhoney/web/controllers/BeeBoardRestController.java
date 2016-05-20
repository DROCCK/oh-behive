package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.*;
import drocck.sp.beesandhoney.business.entities.DTOs.*;
import drocck.sp.beesandhoney.business.services.InspectionService;
import drocck.sp.beesandhoney.business.services.PersonService;
import drocck.sp.beesandhoney.business.services.RegionService;
import drocck.sp.beesandhoney.business.services.YardService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        return filterYards();
    }

    @RequestMapping(value="beeboard/sums", method = RequestMethod.GET)
    public BeeboardSumDTO sums(){
        BeeboardSumDTO bbsdto = new BeeboardSumDTO();
        bbsdto.setYards(yardService.findAllInUseOnlyYard());
        bbsdto.setRegions(regionService.findAll());
        return bbsdto;
    }

    @RequestMapping(value = "beeboard/createYard")
    public YardCreateDTO createYard() {
        return getYardCreateDTO();
    }

    @RequestMapping(value = "beeboard/editYard/{id}", method = RequestMethod.GET)
    public YardEditDTO editOrchard(@PathVariable("id") Long id) {
        return new YardEditDTO(getYardCreateDTO(), yardService.findOne(id));
    }

    @RequestMapping(value = "beeboard/addYard/{json}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Yard addYard(@PathVariable("json") String json) {
        return yardService.save(new JSONObject(json));
    }

    @RequestMapping(value = "beeboard/createInspection/{id}", method = RequestMethod.GET)
    public BeeboardInspectionCreateDTO createInspection(@PathVariable("id") Long id){
        return getBeeboardInspectionCreateDTO(id);
    }
    @RequestMapping(value = "beeboard/addInspection/{json}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Inspection addInspection(@PathVariable("json") String json) {
        Inspection inspection = inspectionService.interpret(new JSONObject(json));
        return inspectionService.save(inspection);
    }
    @RequestMapping(value = "beeboard/changeInactive/{id}", method = RequestMethod.GET)
    public YardEditDTO changeInactive(@PathVariable("id") Long id){
        return new YardEditDTO(getYardCreateDTO(), yardService.findOne(id));
    }

    private YardCreateDTO getYardCreateDTO(){
        YardCreateDTO ycdto = new YardCreateDTO();
        ycdto.setStati(Yard.getStati());
        ycdto.setPeople(personService.findAll());
        ycdto.setRegions(regionService.findAllRegionNames());
        return ycdto;
    }

    private BeeboardInspectionCreateDTO getBeeboardInspectionCreateDTO(Long id){
        BeeboardInspectionCreateDTO bicdto = new BeeboardInspectionCreateDTO();
        bicdto.setYard(yardService.findOne(id));
        return bicdto;
    }

    private List<Region> filterYards(){
        List<Region> regions = new ArrayList<>();
        for(Region r : regionService.findAll()){
            List<Yard> rYards = new ArrayList<>();
            for(Yard y : r.getYards()){
                if(!(y instanceof Orchard) && !(y instanceof NucYard) && y.getStatus().equals(Yard.IN_USE)){
                    rYards.add(y);
                }
            }
            r.setYards(rYards);
            regions.add(r);
        }
        return regions;
    }
}
