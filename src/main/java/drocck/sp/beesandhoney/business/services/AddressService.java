package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Address;
import drocck.sp.beesandhoney.business.entities.repositories.AddressRepository;
import org.hibernate.Hibernate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;


import java.util.List;

/**
 * @author Rob
 *         Created on 9/29/2015.
 */
@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address findOne(Long id) {
        return addressRepository.findOne(id);
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public Address save(JSONObject address) {
        Address a = new Address();
        a.setStreet(address.getString("street"));
        a.setApt(address.getString("suite"));
        a.setCity(address.getString("city"));
        a.setState(address.getString("state"));
        a.setZip(address.getString("zip"));
        return save(a);
    }

    public Address update(Address address) {
        return addressRepository.save(address);
    }

    public void delete(Long id) {
        addressRepository.delete(id);
    }

    public void delete(Address address) {
        addressRepository.delete(address);
    }
}
