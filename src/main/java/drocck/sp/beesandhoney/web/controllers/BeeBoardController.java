package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.DTOs.BeeBoardDTO;
import drocck.sp.beesandhoney.business.services.ShipmentService;
import drocck.sp.beesandhoney.business.services.YardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "dashboard/beeboard", method = RequestMethod.GET)
    public String beeBoard(Model model) {
        BeeBoardDTO beeBoardDTO = new BeeBoardDTO();
        beeBoardDTO.setYards(yardService.findAllInUse());
        beeBoardDTO.setShipments(shipmentService.findAll());
        model.addAttribute("beeBoard", beeBoardDTO);
        return "dashboard/beeboard";
    }
}
