package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Address;
import drocck.sp.beesandhoney.business.entities.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rob
 * Created on 9/29/2015.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAll();
    Address findById(Long id);
    Address save(Address address);
    void delete(Long id);
    void delete(Address address);
}