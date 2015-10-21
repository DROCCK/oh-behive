package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Company;
import drocck.sp.beesandhoney.business.entities.Location;
import drocck.sp.beesandhoney.business.entities.Person;
import drocck.sp.beesandhoney.business.entities.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by Chai on 10/10/2015.
 */
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ContactInfoService contactInfoService;
    @Autowired
    private AddressService addressService;

    public LocationService() {
        super();
    }

    public List<Location> findAll() {
        return this.locationRepository.findAll();
    }

    public Location findById(Long id) {
        Location location = locationRepository.findById(id);
        return location;
    }

    public void delete(Long id){
        locationRepository.delete(id);
    }

    public Location save(final Location location) {
        addressService.save(location.getContactInfo().getAddress());
        return this.locationRepository.save(location);
    }

    public Location update(Location location) {
        Location l = locationRepository.findById(location.getId());
        l.setName(location.getName());
        l.setContactInfo(location.getContactInfo());
        l.getContactInfo().setId(l.getId());
        l.getContactInfo().setAddress(location.getContactInfo().getAddress());
        l.getContactInfo().getAddress().setId(l.getId());
        return locationRepository.save(l);
    }
}