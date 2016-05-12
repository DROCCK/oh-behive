package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Orchard;
import drocck.sp.beesandhoney.business.entities.PolliInspection;
import drocck.sp.beesandhoney.business.entities.repositories.PolliInspectionRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Robert Wilk
 * on 5/9/2016.
 */
@Service
public class PolliInspectionService {

    @Autowired
    private PolliInspectionRepository polliInspectionRepository;

    @Autowired
    private OrchardService orchardService;

    public PolliInspection findOne(Long id) {
        return polliInspectionRepository.findOne(id);
    }

    public List<PolliInspection> findAll() {
        return polliInspectionRepository.findAll();
    }

    public List<PolliInspection> findAllByOrchard(Orchard orchard) {
        return polliInspectionRepository.findAllByOrchard(orchard);
    }

    public PolliInspection save(PolliInspection polliInspection) {
        return polliInspectionRepository.save(polliInspection);
    }

    public PolliInspection save(JSONObject json) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        PolliInspection pi = new PolliInspection();
        try {
            pi.setId(json.getLong("id"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            pi.setDate(
                    new Date(
                            sdf.parse(json.getString("date")).getTime()
                    )
            );
        } catch (ParseException | JSONException jpe) {
            System.err.println(jpe.getMessage());
        }
        try {
            pi.setPurpose(
                    PolliInspection.Purpose.valueOf(
                            json.getString("purpose")
                    )
            );
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            pi.setOrchard(orchardService.findOne(json.getLong("orchard")));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            pi.setNotes(json.getString("notes"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        return save(pi);
    }

    public void delete(Long id) {
        polliInspectionRepository.delete(id);
    }

    public void delete(PolliInspection polliInspection) {
        polliInspectionRepository.delete(polliInspection);
    }
}
