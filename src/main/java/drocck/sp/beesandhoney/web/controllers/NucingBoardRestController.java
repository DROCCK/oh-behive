package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.DTOs.NucReportDTO;
import drocck.sp.beesandhoney.business.entities.DTOs.NucYardCreateDTO;
import drocck.sp.beesandhoney.business.entities.DTOs.YardCreateDTO;
import drocck.sp.beesandhoney.business.entities.NucReport;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.services.NucReportService;
import drocck.sp.beesandhoney.business.services.NucYardService;
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

    @Autowired
    private NucReportService nucReportService;

    @Autowired
    private NucYardService nucYardService;

    @RequestMapping(value = "nucing/createNucYard")
    public YardCreateDTO createYard() {
        YardCreateDTO ycdto = new YardCreateDTO();
        ycdto.setStati(Yard.getStati());
        ycdto.setPeople(personService.findAll());
        ycdto.setRegions(regionService.findAllRegionNames());
        return ycdto;
    }

    @RequestMapping(value = "nucing/nucReport/{id}", method = RequestMethod.GET)
    public NucReport report(@PathVariable("id") Long id) {
        NucReport nucReport;
        nucReport = nucReportService.findOne(id);
        if (nucReport == null) {
            nucReport = new NucReport();
        }
        return nucReport;
    }
}
