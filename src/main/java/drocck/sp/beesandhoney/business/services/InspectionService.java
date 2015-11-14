package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.DropSite;
import drocck.sp.beesandhoney.business.entities.Inspection;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.entities.repositories.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by cjeli_000
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
        DropSite dropSite = dropSiteService.findByInspection(inspection);
        Yard yard = yardService.findByDropSite(dropSite);
        // Subtract the old hive values from the yard count
        yard.setSingles(yard.getSingles() - dropSite.getSingles());
        yard.setDoubles(yard.getDoubles() - dropSite.getDoubles());

        // Update the drop site from the inspection
        dropSite = inspection.getDropSite();
        dropSite.setSingles(inspection.getNumSingles());
        dropSite.setDoubles(inspection.getNumDoubles());

        // Add the new hive values to the yard count
        yard.setSingles(yard.getSingles() + dropSite.getSingles());
        yard.setDoubles(yard.getDoubles() + dropSite.getDoubles());

        inspection.setDropSite(dropSite);
        yardService.save(yard);
        return inspectionRepository.save(inspection);
    }

    public void delete(Inspection inspection) {
        inspectionRepository.delete(inspection);
    }
}
