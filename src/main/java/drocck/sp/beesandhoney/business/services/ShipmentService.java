package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Shipment;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.entities.repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 10/17/2015.
 */
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    public List<Shipment> findAll() {
        return shipmentRepository.findAll();
    }

    public Shipment findById(Long id) {
        return shipmentRepository.findById(id);
    }

    public Shipment findByFromYard(Yard yard) {
        return shipmentRepository.findByFromYard(yard);
    }

    public Shipment findByToYard(Yard yard) {
        return shipmentRepository.findByToYard(yard);
    }

    public Shipment save(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

//    public Shipment save(Shipment shipment) {
//        return shipmentRepository.save(shipment);
//    }

    public void delete(Shipment shipment) {
        shipmentRepository.delete(shipment);
    }

    public void delete(Long id) {
        shipmentRepository.delete(id);
    }
}
