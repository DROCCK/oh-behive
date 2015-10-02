package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Address;
import drocck.sp.beesandhoney.business.entities.Person;
import drocck.sp.beesandhoney.business.entities.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rob
 * Created on 9/29/2015.
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ContactInfoService contactInfoService;

    @Autowired
    private AddressService addressService;

    public List<Person> findAll() {
        List<Person> people = personRepository.findAll();
        people.forEach(
                p -> p.setContactInfo(contactInfoService.findById(p.getId()))
        );
        return people;
    }

    public Person findById(Long id) {
        Person person = personRepository.findById(id);
        person.setContactInfo(contactInfoService.findById(id));
        return person;
    }

    public Person save(Person person) {
        addressService.save(person.getContactInfo().getAddress());
        contactInfoService.save(person.getContactInfo());
        return personRepository.save(person);
    }
}
