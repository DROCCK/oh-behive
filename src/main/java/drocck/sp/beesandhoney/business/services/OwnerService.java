package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Person;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.entities.repositories.OwnerRepository;
import drocck.sp.beesandhoney.business.entities.repositories.PersonRepository;
import drocck.sp.beesandhoney.business.entities.repositories.YardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar on 9/27/2015.
 *
 */
@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private YardRepository yardRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ContactInfoService contactInfoService;
    @Autowired
    private AddressService addressService;

    public OwnerService() {super();}

    public List<Person> findAll() {
        List<Person> people = personRepository.findAll();
        List<Yard> yards = yardRepository.findAll();
        ArrayList<Person> owners = new ArrayList<Person>();
        //O(N^2) WHICH IS SHIT. NEED FIX.
        people.forEach(p -> {
            yards.forEach(y ->{
                if(p.getId().equals(y.getOwner().getId())){
                    p.setContactInfo(contactInfoService.findById(p.getId()));
                    owners.add(p);
                }
            });
        });
        return owners;
    }

    public Yard findYardByID(Long id){
        Yard y = yardRepository.findById(id);
        return y;
    }

    public Person save(Person newOwner) {
        //addressService.save(newOwner.getContactInfo().getAddress());
        contactInfoService.save(newOwner.getContactInfo());
        return personRepository.save(newOwner);
    }

    public void delete(Long id){
        personRepository.delete(id);
    }

    public Person findById(Long id) {
        Person person = personRepository.findById(id);
        return person;
    }
}
