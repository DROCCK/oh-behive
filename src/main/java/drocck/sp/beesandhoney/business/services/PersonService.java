package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Person;
import drocck.sp.beesandhoney.business.entities.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rob
 *         Created on 9/29/2015.
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

    public Person findOne(Long id) {
        return personRepository.findOne(id);
    }

    public Person findOneByName(String name) {
        return personRepository.findOneByName(name);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person update(Person person) {
        return personRepository.save(person);
    }

    public void delete(Long id) {
        personRepository.delete(id);
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }
}
