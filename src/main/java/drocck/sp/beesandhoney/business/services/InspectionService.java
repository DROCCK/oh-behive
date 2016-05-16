package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Inspection;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.entities.repositories.InspectionRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by Connor
 * on 10/9/2015.
 */
public class InspectionService {

    @Autowired
    private InspectionRepository inspectionRepository;


    @Autowired
    private YardService yardService;

    public Inspection findOne(Long id) {
        return inspectionRepository.findOne(id);
    }

    public List<Inspection> findAll() {
        return inspectionRepository.findAll();
    }

    public List<Inspection> findAllByYard(Yard yard) {
        return inspectionRepository.findAllByYard(yard);
    }

    public List<Inspection> findAllByYardAndVisitDateBetween(Yard yard, java.sql.Date d1, java.sql.Date d2) {
        return inspectionRepository.findAllByYardAndVisitDateBetween(yard, d1, d2);
    }

    public Inspection update(Inspection inspection) {
        return inspectionRepository.save(inspection);
    }

    public Inspection save(Inspection inspection) {
        /*OLD
        // Get the old drop site and associated yard
        Yard yard = inspection.getYard();

        int oldSingles = yard.getSingles();
        int oldDoubles = yard.getDoubles();

        // Update the drop site from the inspection
        yard.setSingles(inspection.getNumSingles());
        yard.setDoubles(inspection.getNumDoubles());
        yard.setSupers(inspection.getSupers());

        int newSingles = yard.getSingles();
        int newDoubles = yard.getDoubles();

        // update duds
        int diff = (oldSingles + oldDoubles) - (newSingles + newDoubles);
        if (yard.getLastVisit() == null)
            diff = 0;
        yard.setDuds(yard.getDuds() + diff);
        // Update last visit date
        yard.setLastVisit(inspection.getVisitDate());
        if (inspection.isFed()) {
            yard.setLastFedDate(inspection.getVisitDate());
        }
        */
        Yard yard = inspection.getYard();
        yard.setSingles(inspection.getSingles());
        yard.setDoubles(inspection.getDoubles());
        yard.setSupers(inspection.getSupers());
        yard.setDuds(inspection.getDuds());

        if (inspection.isFed()) {
            yard.setLastFedDate(inspection.getVisitDate());
        }

        yard.setLastVisit(inspection.getVisitDate());

        inspection.setYard(yard);
        yardService.save(yard);
        return inspectionRepository.save(inspection);
    }

    public Inspection interpret(JSONObject json){
        //create inspection
        Inspection inspection = new Inspection();
        try {
            inspection.setYard(yardService.findOne(json.getLong("yard")));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            Date parsed = format.parse(json.getString("date"));
            java.sql.Date sql = new java.sql.Date(parsed.getTime());
            inspection.setVisitDate(sql);
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            inspection.setDoubles(json.getInt("doubles"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            inspection.setSingles(json.getInt("singles"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            inspection.setSupers(json.getInt("supers"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            inspection.setDuds(json.getInt("duds"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            inspection.setMedication(json.getString("medication"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            inspection.setNotes(json.getString("notes"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            if(json.getString("isFed").equals("fed")){
                inspection.setIsFed(true);
            }
            else{
                inspection.setIsFed(false);
            }
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        return inspection;
    }
    public void delete(Inspection inspection) {
        inspectionRepository.delete(inspection);
    }
}
