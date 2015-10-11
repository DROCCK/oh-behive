package drocck.sp.beesandhoney.business.services;
import drocck.sp.beesandhoney.business.entities.DropSite;
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

    public DropSiteService() {
        super();
    }

    public List<DropSite> findAll() {
        return this.dropSiteRepository.findAll();
    }

    public DropSite findById(Long id) {
        DropSite ID = dropSiteRepository.findById(id);
        return ID;
    }

    /*public DropSite findByDate(java.sql.Date date){
        DropSite dropDate = dropSiteRepository.findByDate(date);
        return dropDate;
    }*/

    public void deleteInBatch(ArrayList<DropSite> entities) {
        dropSiteRepository.deleteInBatch(entities);
    }

    public DropSite save(final DropSite dropSite) {
        return this.dropSiteRepository.save(dropSite);
    }
}