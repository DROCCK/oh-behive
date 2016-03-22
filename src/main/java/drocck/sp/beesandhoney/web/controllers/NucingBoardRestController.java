package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.*;
import drocck.sp.beesandhoney.business.entities.DTOs.YardCreateDTO;
import drocck.sp.beesandhoney.business.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Connor on 3/12/2016.
 */
@RestController
public class NucingBoardRestController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private PersonService personService;

    @Autowired
    private OwnerService ownerService;

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
        nucReport = nucReportService.findOneByYard(nucYardService.findOne(id));
        if (nucReport == null) {
            nucReport = new NucReport();
        }
        return nucReport;
    }

    @RequestMapping(value = "nucing/update/nucYard", method = RequestMethod.POST)
    public void addNucYard(@RequestBody String nucYard) {
        Map<String, String> keyValMap = parseString(nucYard);
        NucYard y = nucYardService.findOneByName(keyValMap.get("yardName"));
        Address a;
        Owner o = ownerService.findOneByPerson(personService.findOneByName(keyValMap.get("owner")));
        if (y == null) {
            y = new NucYard();
            a = new Address();
        } else {
            a = y.getAddress();
        }
        if (o == null) {
            o = new Owner();
            o.setPerson(personService.findOneByName(keyValMap.get("owner")));
        }
        y.setYardName(keyValMap.get("yardName"));
        if (keyValMap.get("maxHives") != "") {
            y.setMaxHives(Integer.parseInt(keyValMap.get("maxHives")));
        } else {
            y.setMaxHives(0);
        }
        y.setStatus(keyValMap.get("status"));
        y.setOwner(o);
        y.setRentReceiver(personService.findOneByName(keyValMap.get("rentReceiver")));
        y.setLongitude(Double.parseDouble(keyValMap.get("longitude")));
        y.setLatitude(Double.parseDouble(keyValMap.get("latitude")));
        y.setCombo(keyValMap.get("combo"));
        y.setRegion(regionService.findByName(keyValMap.get("region")));


        a.setStreet(keyValMap.get("street"));
        a.setApt(keyValMap.get("apt"));
        a.setCity(keyValMap.get("city"));
        a.setState(keyValMap.get("state"));
        a.setZip(keyValMap.get("zip"));

        y.setAddress(a);

        if (o.getYards() == null) {
            ArrayList<Yard> list = new ArrayList();
            list.add(y);
            o.setYards(list);
        } else {
            o.getYards().add(y);
        }

        System.out.println(nucYard);

        nucYardService.save(y);
    }

    @RequestMapping(value = "nucing/update/nucReport", method = RequestMethod.POST)
    public void addNucReport(@RequestBody String nucReport) {
        System.out.println(nucReport);
        Map<String, String> reportMap = parseString(nucReport);
        Yard y = nucYardService.findOne(Long.parseLong(reportMap.get("yardId")));
        NucReport report = nucReportService.findOneByYard(y);
        if (report == null) {
            report = new NucReport();
            report.setYard(y);
        }

        SimpleDateFormat sdf = new SimpleDateFormat();
        java.util.Date lDate = null;
        java.util.Date pDate = null;
        java.util.Date sDate = null;
        java.util.Date splitDate = null;

        try {
            lDate = sdf.parse(reportMap.get("dateLaidOut"));
            pDate = sdf.parse(reportMap.get("dateBeesPlaced"));
            sDate = sdf.parse(reportMap.get("dateBeesSupered"));
            splitDate = sdf.parse(reportMap.get("dateBeesSplit"));
        } catch (ParseException pe) {
            System.err.println(pe.getMessage());
        }

        if (lDate != null) {
            report.setDateLaidOut(new Date(lDate.getTime()));
        }

        if (pDate != null) {
            report.setDateBeesPlaced(new Date(pDate.getTime()));
        }

        report.setInitialCount(Integer.parseInt(reportMap.get("initalCount")));

        if (sDate != null) {
            report.setDateBeesSupered(new Date(sDate.getTime()));
        }

        report.setSuperCount(Integer.parseInt(reportMap.get("superCount")));

        if (splitDate != null) {
            report.setDateBeesSplit(new Date(splitDate.getTime()));
        }

        report.setOldQueensCount(Integer.parseInt(reportMap.get("oldQueensCount")));
        report.setNucCount(Integer.parseInt(reportMap.get("nucCount")));
        report.setQueensPlaced(Integer.parseInt(reportMap.get("queensPlaced")));
        report.setFinalCount(Integer.parseInt(reportMap.get("finalCount")));

        nucReportService.save(report);
    }

//    @RequestMapping(value = "nucing/test", method = RequestMethod.POST)
//    public @ResponseBody
//    NucingTask test(@RequestBody final String event) {
//        Map<String, String> m = parseString(event);
//
//        int count = Integer.parseInt(m.get("count"));
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date d = new java.sql.Date(0);
//        try {
//            java.util.Date day = sdf.parse(m.get("start"));
//            d.setTime(day.getTime());
//        } catch (ParseException pe) {
//            System.err.println("Parsing Date Failed " + pe.getMessage());
//        }
//
//        NucingTask task = new NucingTask(count, d);
//
//        return task;
//    }

    private Map<String, String> parseString(String s) {
        Map<String, String> m = new HashMap<>();
        String key = "";
        String value = "";
        s += '&';
        boolean writeToKey = true;
        int i = 0;
        while (i < s.length()) {
            switch (s.charAt(i)) {
                case '%':
                    i++;
                    String hex = s.substring(i, (i + 2));
                    i++;
                    if (writeToKey) {
                        key += (char) (Integer.parseInt(hex, 16));
                    } else {
                        value += (char) (Integer.parseInt(hex, 16));
                    }
                    break;
                case '+':
                    if (writeToKey) {
                        key += " ";
                    } else {
                        value += " ";
                    }
                    break;
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
}
