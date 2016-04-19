package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Owner;
import drocck.sp.beesandhoney.business.entities.Person;
import drocck.sp.beesandhoney.business.entities.Region;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.entities.repositories.RegionRepository;
import drocck.sp.beesandhoney.business.entities.repositories.YardRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Connor
 * on 9/26/2015.
 */
@Service
public class YardService {

    @Autowired
    private YardRepository yardRepository;

    @Autowired
    private RegionService regionService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PersonService personService;

    @Autowired
    private OwnerService ownerService;

    public List<Yard> findAll() {
        return this.yardRepository.findAll();
    }

    public List<Yard> findAllInUse() {
        return findAll().stream().filter(
            yard -> yard.getStatus().equals(Yard.IN_USE)).collect(Collectors.toList()
        );
    }

    public List<Yard> findAllByOwner(Owner owner) {
        return yardRepository.findAllByOwner(owner);
    }

    public List<Yard> findAllByRegion(Region region){
        return yardRepository.findAllByRegion(region);
    }

    public Yard findOne(Long id) {
        return yardRepository.findOne(id);
    }

    public void deleteInBatch(Iterable<Yard> entities) {
        yardRepository.deleteInBatch(entities);
    }

    public Yard save(final Yard yard) {
        //Region region=regionService.findOne(yard.getRegion().getId());
        //region.setYards(yardRepository.findAllByRegion(region));
        //regionService.save(region);
        return yardRepository.save(yard);
    }

    public Yard save(JSONObject json){
        Yard yard = new Yard();
        try {
            yard.setId(json.getLong("id"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            yard.setYardName(json.getString("yardName"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            yard.setStatus(json.getString("status"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            yard.setMaxHives(json.getInt("maxHives"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            yard.setOwner(ownerService.findOne(json.getLong("owner")));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            yard.setRentReceiver(personService.findOne(json.getLong("rentReceiver")));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            yard.setLatitude(json.getDouble("latitudeModal"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            yard.setLongitude(json.getDouble("longitudeModal"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            yard.setRegion(regionService.findByName(json.getString("region")));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            yard.setCombo(json.getString("combo"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            yard.setAccessNotes(json.getString("accessNotes"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        try {
            yard.setAddress(addressService.save(json.getJSONObject("address")));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        return yardRepository.save(yard);
    }
}
