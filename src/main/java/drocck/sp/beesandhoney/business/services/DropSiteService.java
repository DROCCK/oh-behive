package drocck.sp.beesandhoney.business.services;
import drocck.sp.beesandhoney.business.entities.DropSite;
import drocck.sp.beesandhoney.business.entities.Inspection;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.entities.repositories.DropSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by David on 10/9/2015.
 */
@Service
public class DropSiteService {


    @Autowired
    private DropSiteRepository dropSiteRepository;

    public List<DropSite> findAll() {
        return this.dropSiteRepository.findAll();
    }

    public List<DropSite> findAllByDropYard(Yard yard) {
        return dropSiteRepository.findAllByDropYard(yard);
    }

    public DropSite findOne(Long id) {
        return dropSiteRepository.findOne(id);
    }

    public void deleteInBatch(ArrayList<DropSite> entities) {
        dropSiteRepository.deleteInBatch(entities);
    }

    public DropSite save(final DropSite dropSite) {
        return this.dropSiteRepository.save(dropSite);
    }

    public DropSite findByInspection(Inspection inspection) {
        return dropSiteRepository.findByInspections(inspection);
    }
}