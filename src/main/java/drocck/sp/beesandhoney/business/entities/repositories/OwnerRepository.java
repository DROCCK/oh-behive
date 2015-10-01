package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar on 9/27/2015.
 * Based on Yard repository
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    public List<Owner> findAll();

    public Owner save(Owner newOwner);
}
