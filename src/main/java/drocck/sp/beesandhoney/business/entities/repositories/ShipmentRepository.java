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
    List<Shipment> findAll();
    Shipment findById(Long id);
    Shipment findByToYard(Yard yard);
    Shipment findByFromYard(Yard yard);
//    Shipment save(Shipment shipment);
//    Shipment save(Long id);
//    void delete(Shipment shipment);
//    void delete(Long id);
}
