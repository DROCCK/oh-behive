package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.PolliShipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Robert on 3/5/2016.
 */
@Repository
public interface PolliShipmentRepository extends JpaRepository<PolliShipment, Long> {
}
