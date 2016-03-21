package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.PolliShipment;
import drocck.sp.beesandhoney.business.entities.repositories.PolliShipmentRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Robert on 3/5/2016.
 */
@Service
public class PolliShipmentService {

    @Autowired
    private PolliShipmentRepository polliShipmentRepository;

    public List<PolliShipment> findAll() {
        return polliShipmentRepository.findAll();
    }

    public PolliShipment findOne(Long id) {
        return polliShipmentRepository.findOne(id);
    }

    public PolliShipment save(PolliShipment polliShipment) {
        return polliShipmentRepository.save(polliShipment);
    }

    public PolliShipment save(JSONObject json) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        PolliShipment p = new PolliShipment();
        try {
            p.setDate(
                new Date(
                    sdf.parse(json.getString("date")).getTime()
                )
            );
        } catch (ParseException pe) {
            System.err.println(pe.getMessage());
        }
        try {
            p.setDirection(
                    PolliShipment.Direction.valueOf(
                            json.getString("direction")
                    )
            );
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            p.setIn(json.getInt("in"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            p.setDud(json.getInt("dud"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            p.setFrom(json.getString("from"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            p.setTo(json.getString("to"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            p.setNotes(json.getString("notes"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        return save(p);
    }

    public void delete(Long id) {
        polliShipmentRepository.delete(id);
    }

    public void delete(PolliShipment polliShipment) {
        polliShipmentRepository.delete(polliShipment);
    }
}
