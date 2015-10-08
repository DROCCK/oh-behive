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
        contactInfoService.save(person.getContactInfo());
        return personRepository.save(person);
    }

    public Person update(Person person) {
        Person p = personRepository.findById(person.getId());
        p.setName(person.getName());
        person.getContactInfo().setId(p.getId());
        p.setContactInfo(contactInfoService.update(person.getContactInfo()));
        return personRepository.save(p);
    }

    public void delete(Long id) {
        personRepository.delete(id);
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }
}
