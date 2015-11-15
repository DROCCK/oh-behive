package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Owner;
import drocck.sp.beesandhoney.business.entities.Yard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Oscar on 9/27/2015.
 *
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {
    Owner findByYards(Yard yard);
}
