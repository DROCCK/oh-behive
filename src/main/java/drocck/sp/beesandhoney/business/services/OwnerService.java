package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Owner;
import drocck.sp.beesandhoney.business.entities.Person;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.entities.repositories.OwnerRepository;
import drocck.sp.beesandhoney.business.entities.repositories.PersonRepository;
import drocck.sp.beesandhoney.business.entities.repositories.YardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Oscar on 9/27/2015.
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

    public List<Owner> findAll() {
        return ownerRepository.findAll();
        /*List<Person> people = personRepository.findAll();
        List<Yard> yards = yardRepository.findAll();
        ArrayList<Person> owners = new ArrayList<Person>();
        //O(N^2) WHICH IS SHIT. NEED FIX.
        people.forEach(p -> {
            yards.forEach(y ->{
                if(p.getId().equals(y.getOwner().getId())){
                    p.setContactInfo(contactInfoService.findOne(p.getId()));
                    owners.add(p);
                }
            });
        });
        return owners;*/
    }

    public Owner findOne(Long id) {
        return ownerRepository.findOne(id);
    }

    public Owner findOneByPerson(Person p) {
        return ownerRepository.findOneByPerson(p);
    }

    public void delete(Owner owner) {
        ownerRepository.delete(owner);
    }

    public void delete(Long id) {
        ownerRepository.delete(id);
    }

    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Owner update(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Yard findYard(Long id) {
        List<Yard> yard = yardRepository.findAll();
        final Yard[] ownedYard = new Yard[1];
        Yard last;
        yard.forEach(y -> {
            if (y.getOwner().getId().equals(id)) {
                ownedYard[0] = y;   //not entirely sure why this was necessary
            }
        });
        last = ownedYard[0];
        return last;
    }
}
