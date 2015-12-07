package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.DropSite;
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

    private DropSiteService dropSiteService;

    @Autowired
    private YardService yardService;

    public Inspection findOne(Long id) {
        return inspectionRepository.findOne(id);
    }

    public List<Inspection> findAll() {
        return inspectionRepository.findAll();
    }

    public List<Inspection> findAllByDropSite(DropSite dropsite) {
        return inspectionRepository.findAllByDropSite(dropsite);
    }

    public Inspection update(Inspection inspection) {
        return inspectionRepository.save(inspection);
    }

    public Inspection save(Inspection inspection) {
        // Get the old drop site and associated yard
        DropSite dropSite = inspection.getDropSite();
        Yard yard = dropSite.getDropYard();

        int oldSingles = yard.getSingles();
        int oldDoubles = yard.getDoubles();

        // Subtract the old hive values from the yard count
        yard.setSingles(oldSingles - dropSite.getSingles());
        yard.setDoubles(oldDoubles - dropSite.getDoubles());
        yard.setSupers(yard.getSupers() - dropSite.getSupers());

        // Update the drop site from the inspection
        dropSite.setSingles(inspection.getNumSingles());
        dropSite.setDoubles(inspection.getNumDoubles());
        dropSite.setSupers(inspection.getSupers());

        // Add the new hive values to the yard count
        yard.setSingles(yard.getSingles() + dropSite.getSingles());
        yard.setDoubles(yard.getDoubles() + dropSite.getDoubles());
        yard.setSupers(yard.getSupers() + dropSite.getSupers());

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
        inspection.setDropSite(dropSite);
        yardService.save(yard);
        return inspectionRepository.save(inspection);
    }

    public void delete(Inspection inspection) {
        inspectionRepository.delete(inspection);
    }
}
