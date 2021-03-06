package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Shipment;
import drocck.sp.beesandhoney.business.entities.Yard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 10/17/2015.
 */
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Shipment findByToYard(Yard yard);
    Shipment findByFromYard(Yard yard);
    List<Shipment> findAllByToYard(Yard yard);
    List<Shipment> findAllByFromYard(Yard yard);
}
