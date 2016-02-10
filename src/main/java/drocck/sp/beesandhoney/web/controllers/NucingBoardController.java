package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.DTOs.NucingBoardDTO;
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
import java.util.*;

/**
 * Created by Chai on 10/31/2015.
 */

@Controller
public class NucingBoardController {

    //FOR TESTING
    List<Event> eventList = new Vector<>();

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
//        List<Event> eventList = new Vector<>();
//        Event e1 = new Event();
//        e1.setTitle("First");
//        e1.setStart(new Date());
//        e1.setColor("yellow");
//        eventList.add(e1);
//        Event e2 = new Event();
//        e2.setTitle("Second");
//        e2.setStart(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)));
//        eventList.add(e2);
        return eventList;
    }

    @ResponseBody
    @RequestMapping("nucing/yards")
    public List<Yard> yardFeed() {
        // TODO: CHANGE THIS TO NUCING SPECIFIC YARDS
        List<Yard> yardList = yardService.findAllInUse();
        return yardList;
    }

    @RequestMapping(value = "nucing/test", method = RequestMethod.POST)
    public @ResponseBody Event test(@RequestBody final String event) {
        Map<String, String> m = parseString(event);

        Event e = new Event();
        e.setTitle("Place " + m.get("count") + " queens");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        try {
            d = sdf.parse(m.get("start"));
        } catch (ParseException pe) {
            System.err.println("Parsing Date Failed" + pe.getMessage());
        }
        e.setStart(d);
        eventList.add(e);

        Event nuc = new Event();
        nuc.setTitle("Make " + m.get("count") + " nucs");
        long dayInMilli = 1000 * 60 * 60 * 24;
        nuc.setStart(new Date(d.getTime() - (2 * dayInMilli)));
        nuc.setColor("red");
        eventList.add(nuc);

        Event check = new Event();
        check.setTitle("Check " + m.get("count") + " nucs");
        check.setStart(new Date(d.getTime() + (21 * dayInMilli)));
        check.setColor("green");
        eventList.add(check);

        return e;
    }

    private Map<String, String> parseString(String s) {
        Map<String, String> m = new HashMap();
        String key = "";
        String value = "";
        s+='&';
        boolean writeToKey = true;
        int i = 0;
        while (i < s.length()) {
            switch (s.charAt(i)) {
                // end of key start of value
                case '=':
                    writeToKey = false;
                    break;
                // end of value start of new entry
                case '&':
                    m.put(key, value);
                    writeToKey = true;
                    // clear key and value
                    key = "";
                    value = "";
                    break;
                default:
                    if (writeToKey) {
                        key += s.charAt(i);
                    } else {
                        value += s.charAt(i);
                    }
                    break;
            }
            i++;
        }
        return m;
    }

    public class Event {
        private String title;
        private Date start;
        private boolean allDay = true;
        private String color;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Date getStart() {
            return start;
        }

        public void setStart(Date start) {
            this.start = start;
        }

        public boolean isAllDay() {
            return allDay;
        }

        public void setAllDay(boolean allDay) {
            this.allDay = allDay;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}