package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.DTOs.NucingBoardDTO;
import drocck.sp.beesandhoney.business.services.ShipmentService;
import drocck.sp.beesandhoney.business.services.YardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Chai on 10/31/2015.
 */

@Controller
public class NucingBoardController {

    @Autowired
    private YardService yardService;

    @Autowired
    private ShipmentService shipmentService;

    @RequestMapping(value = "dashboard/nucing", method = RequestMethod.GET)
    public String nucingBoard(Model model) {
        NucingBoardDTO nucingBoardDTO = new NucingBoardDTO();
        nucingBoardDTO.setYards(yardService.findAllInUse());
        nucingBoardDTO.setShipments(shipmentService.findAll());
        model.addAttribute("nucing", nucingBoardDTO);
        return "dashboard/nucing";
    }
}