package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Region;
import drocck.sp.beesandhoney.business.entities.Yard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kyle on 11/21/2015.
 */
public interface RegionRepository extends JpaRepository<Region, Long> {
    Region findByYards(Yard yard);
}
