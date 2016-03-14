package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.DTOs.NucReportDTO;
import drocck.sp.beesandhoney.business.entities.DTOs.NucYardCreateDTO;
import drocck.sp.beesandhoney.business.entities.DTOs.YardCreateDTO;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.services.PersonService;
import drocck.sp.beesandhoney.business.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Connor on 3/12/2016.
 */
@RestController
public class NucingBoardRestController {

    @Autowired
    private RegionService regionService;

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "nucing/createNucYard")
    public YardCreateDTO createYard() {
        YardCreateDTO ycdto = new YardCreateDTO();
        ycdto.setStati(Yard.getStati());
        ycdto.setPeople(personService.findAll());
        ycdto.setRegions(regionService.findAllRegionNames());
        return ycdto;
    }

    @RequestMapping(value = "nucing/nucRepor/{id}", method = RequestMethod.GET)
    public NucReportDTO report(@PathVariable("id") Long id) {
        NucReportDTO nucReport = new NucReportDTO();
        //todo: fill DTO
        return nucReport;
    }
}
