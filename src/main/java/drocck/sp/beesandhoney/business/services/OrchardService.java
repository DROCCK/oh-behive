package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Orchard;
import drocck.sp.beesandhoney.business.entities.repositories.OrchardRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 2/2/2016.
 */
@Service
public class OrchardService {

    @Autowired
    private OrchardRepository orchardRepository;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private PersonService personService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private AddressService addressService;

    public Orchard findOne(Long id) {
        return orchardRepository.findOne(id);
    }

    public Orchard findOneByName(String name) {
        return orchardRepository.findOneByYardName(name);
    }

    public List<Orchard> findAll() {
        return orchardRepository.findAll();
    }

    public Orchard save(Orchard orchard) {
        return orchardRepository.save(orchard);
    }

    public Orchard save(JSONObject json) {
        Orchard orchard = new Orchard();
        try {
            orchard = orchardRepository.findOne(json.getLong("id"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            orchard.setYardName(json.getString("yardName"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            orchard.setStatus(json.getString("status"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            orchard.setMaxHives(json.getInt("maxHives"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            orchard.setOwner(ownerService.findOne(json.getLong("owner")));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            orchard.setRentReceiver(personService.findOne(json.getLong("rentReceiver")));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            orchard.setLatitude(json.getDouble("latitude"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            orchard.setLongitude(json.getDouble("longitude"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            orchard.setRegion(regionService.findByName(json.getString("region")));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            orchard.setCombo(json.getString("combo"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            orchard.setAccessNotes(json.getString("accessNotes"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            orchard.setAddress(addressService.save(json.getJSONObject("address")));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        return save(orchard);
    }

    public void delete(Orchard orchard) {
        orchardRepository.delete(orchard);
    }

    public void delete(Long id) {
        orchardRepository.delete(id);
    }

    public List<String> findAllOrchardNames() {
        return orchardRepository.findAllOrchardNames();
    }
}
