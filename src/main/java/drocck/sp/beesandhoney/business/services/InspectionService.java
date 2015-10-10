package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Dropsite;
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

    /**
     * @autowired
     * private DropsiteService dropsiteService
     */

    public Inspection findById(Long id) {
        return inspectionRepository.findById(id);
    }

    public List<Inspection> findByDropsite(Dropsite dropsite) {
        return inspectionRepository.findByDropsite(dropsite);
    }

    public Inspection save(Inspection inspection) {
        //dropsiteService.save(inspection.getDropsite());
        return inspectionRepository.save(inspection);
    }

    public void delete(Inspection inspection) {
        inspectionRepository.delete(inspection);
    }
}
