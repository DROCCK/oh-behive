package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Inspection;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.entities.repositories.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Connor
 * on 10/9/2015.
 */
public class InspectionService {

    @Autowired
    private InspectionRepository inspectionRepository;


    @Autowired
    private YardService yardService;

    public Inspection findOne(Long id) {
        return inspectionRepository.findOne(id);
    }

    public List<Inspection> findAll() {
        return inspectionRepository.findAll();
    }

    public List<Inspection> findAllByYard(Yard yard) {
        return inspectionRepository.findAllByYard(yard);
    }

    public Inspection update(Inspection inspection) {
        return inspectionRepository.save(inspection);
    }

    public Inspection save(Inspection inspection) {
        // Get the old drop site and associated yard
        Yard yard = inspection.getYard();

        int oldSingles = yard.getSingles();
        int oldDoubles = yard.getDoubles();

        // Update the drop site from the inspection
        yard.setSingles(inspection.getNumSingles());
        yard.setDoubles(inspection.getNumDoubles());
        yard.setSupers(inspection.getSupers());

        int newSingles = yard.getSingles();
        int newDoubles = yard.getDoubles();

        // update duds
        int diff = (oldSingles + oldDoubles) - (newSingles + newDoubles);
        if (yard.getLastVisit() == null)
            diff = 0;
        yard.setDuds(yard.getDuds() + diff);
        // Update last visit date
        yard.setLastVisit(inspection.getVisitDate());
        if (inspection.isFed()) {
            yard.setLastFedDate(inspection.getVisitDate());
        }
        inspection.setYard(yard);
        yardService.save(yard);
        return inspectionRepository.save(inspection);
    }

    public void delete(Inspection inspection) {
        inspectionRepository.delete(inspection);
    }
}
