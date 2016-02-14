package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.DTOs.BeeBoardDTO;
import drocck.sp.beesandhoney.business.entities.Region;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.services.RegionService;
import drocck.sp.beesandhoney.business.services.ShipmentService;
import drocck.sp.beesandhoney.business.services.YardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 10/20/2015.
 */
@Controller
public class BeeBoardController {

    @Autowired
    private YardService yardService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private ShipmentService shipmentService;

    private List<Region> regionList;

    @RequestMapping("dashboard/beeboard/json")
    @ResponseBody
    public List<Region> json() {
        return regionList;
    }

    @ModelAttribute("yard")
    public Yard constructYard() {
        return new Yard();
    }

    @RequestMapping(value = "dashboard/beeboard", method = RequestMethod.GET)
    public String beeBoard(Model model) {
        BeeBoardDTO beeBoardDTO = new BeeBoardDTO();
        regionList=regionService.findAll();
        beeBoardDTO.setRegions(regionList);
        beeBoardDTO.setYards(yardService.findAllInUse());
        beeBoardDTO.setShipments(shipmentService.findAll());
        model.addAttribute("beeBoard", beeBoardDTO);
        return "dashboard/beeboard";
    }
}
