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

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        return personRepository.findById(id);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person update(Person person) {
        Person p = personRepository.findById(person.getId());
        p.setName(person.getName());
        p.setContactInfo(person.getContactInfo());
        p.getContactInfo().setId(p.getId());
        p.getContactInfo().setAddress(person.getContactInfo().getAddress());
        p.getContactInfo().getAddress().setId(p.getId());
        return personRepository.save(p);
    }

    public void delete(Long id) {
        personRepository.delete(id);
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }
}
