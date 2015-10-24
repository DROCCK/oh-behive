package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Address;
import drocck.sp.beesandhoney.business.entities.ContactInfo;
import drocck.sp.beesandhoney.business.entities.repositories.AddressRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rob
 * Created on 9/29/2015.
 */
@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address findById(Long id) {
        return addressRepository.findById(id);
    }

    public Address save(Address address) {
        Hibernate.initialize(address);
        return addressRepository.save(address);
    }

    public Address update(Address address) {
        Address a = addressRepository.findById(address.getId());
        a.setStreet(address.getStreet());
        a.setApt(address.getApt());
        a.setCity(address.getCity());
        a.setState(address.getState());
        a.setZip(address.getZip());
        return addressRepository.save(a);
    }

    public void delete(Long id) {
        addressRepository.delete(id);
    }

    public void delete(Address address) {
        addressRepository.delete(address);
    }
}
