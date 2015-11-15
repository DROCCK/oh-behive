package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.ContactInfo;
import drocck.sp.beesandhoney.business.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rob
 * Created on 9/29/2015.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> { }
