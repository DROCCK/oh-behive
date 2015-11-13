package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.DropSite;
import drocck.sp.beesandhoney.business.entities.Inspection;
import drocck.sp.beesandhoney.business.entities.repositories.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by cjeli_000 on 10/9/2015.
 */
public class InspectionService {

    @Autowired
    private InspectionRepository inspectionRepository;

    public Inspection findById(Long id) {
        return inspectionRepository.findById(id);
    }

    public List<Inspection> findByDropsite(DropSite dropsite) {
        return inspectionRepository.findAllByDropSite(dropsite);
    }

    public Inspection update(Inspection inspection) {
        return inspectionRepository.save(inspection);
    }

    public Inspection save(Inspection inspection) {
        return inspectionRepository.save(inspection);
    }

    public void delete(Inspection inspection) {
        inspectionRepository.delete(inspection);
    }
}
