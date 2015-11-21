package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.DTOs.BeeBoardDTO;
import drocck.sp.beesandhoney.business.entities.DropSite;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.services.DropSiteService;
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
    private ShipmentService shipmentService;

    @Autowired
    private DropSiteService dropSiteService;

    private List<Yard> activeYards;

    @RequestMapping("dashboard/beeboard/json")
    @ResponseBody
    public List<Yard> json() {
        return activeYards;
    }

    @ModelAttribute("yard")
    public Yard constructYard() {
        return new Yard();
    }

    @RequestMapping(value = "dashboard/beeboard", method = RequestMethod.GET)
    public String beeBoard(Model model) {
        BeeBoardDTO beeBoardDTO = new BeeBoardDTO();
        activeYards=yardService.findAllInUse();
        for(Yard y : activeYards) {
            y.setDrops(dropSiteService.findAllByDropYard(y));
        }
        beeBoardDTO.setYards(activeYards);
        beeBoardDTO.setShipments(shipmentService.findAll());
        model.addAttribute("beeBoard", beeBoardDTO);
        return "dashboard/beeboard";
    }
}
