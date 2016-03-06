package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.PolliShipment;
import drocck.sp.beesandhoney.business.entities.repositories.PolliShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Robert on 3/5/2016.
 */
@Service
public class PolliShipmentService {

    @Autowired
    private PolliShipmentRepository polliShipmentRepository;

    public List<PolliShipment> findAll() {
        return polliShipmentRepository.findAll();
    }

    public PolliShipment findOne(Long id) {
        return polliShipmentRepository.findOne(id);
    }

    public PolliShipment save(PolliShipment polliShipment) {
        return polliShipmentRepository.save(polliShipment);
    }

    public void delete(Long id) {
        polliShipmentRepository.delete(id);
    }

    public void delete(PolliShipment polliShipment) {
        polliShipmentRepository.delete(polliShipment);
    }
}
