package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Orchard;
import drocck.sp.beesandhoney.business.entities.PolliInspection;
import drocck.sp.beesandhoney.business.entities.repositories.PolliInspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Robert Wilk
 * on 5/9/2016.
 */
@Service
public class PolliInspectionService {

    @Autowired
    private PolliInspectionRepository polliInspectionRepository;

    @Autowired
    private OrchardService orchardService;

    public PolliInspection findOne(Long id) {
        return polliInspectionRepository.findOne(id);
    }

    public List<PolliInspection> findAll() {
        return polliInspectionRepository.findAll();
    }

    public List<PolliInspection> findAllByOrchard(Orchard orchard) {
        return polliInspectionRepository.findAllByOrchard(orchard);
    }

    public PolliInspection save(PolliInspection polliInspection) {
        return polliInspectionRepository.save(polliInspection);
    }

    public void delete(Long id) {
        polliInspectionRepository.delete(id);
    }

    public void delete(PolliInspection polliInspection) {
        polliInspectionRepository.delete(polliInspection);
    }
}
