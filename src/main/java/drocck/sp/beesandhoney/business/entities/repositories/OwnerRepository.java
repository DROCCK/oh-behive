package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Oscar on 9/27/2015.
 *
 */
@Repository
public interface OwnerRepository extends JpaRepository<Person, Long>{
    public List<Person> findAll();
    public Person save(Person newOwner);
    public Person findById(Long id);
}
