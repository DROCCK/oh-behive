package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.DTOs.NucingBoardDTO;
import drocck.sp.beesandhoney.business.entities.Event;
import drocck.sp.beesandhoney.business.entities.NucingTask;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.services.ShipmentService;
import drocck.sp.beesandhoney.business.services.YardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Chai on 10/31/2015.
 *
 */

@Controller
public class NucingBoardController {

    //FOR TESTING
    List<NucingTask> taskList = new Vector<>();

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

    @ResponseBody
    @RequestMapping("nucing/events")
    public List<Event> eventFeed() {
        // TODO: CHANGE THIS FROM HARD CODED TO RETRIEVE FROM DB
        List<Event> eventList = new Vector<>();
        for (NucingTask t : taskList)
            eventList.addAll(t.getEvent().stream().collect(Collectors.toList()));
        return eventList;
    }

    @ResponseBody
    @RequestMapping("nucing/yards")
    public List<Yard> yardFeed() {
        // TODO: CHANGE THIS TO NUCING SPECIFIC YARDS
        return yardService.findAllInUse();
    }


}