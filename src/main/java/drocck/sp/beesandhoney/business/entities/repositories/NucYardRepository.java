package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.NucYard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Chai on 3/8/2016.
 */
@Repository
public interface NucYardRepository extends JpaRepository<NucYard, Long> {
}
