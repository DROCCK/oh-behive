package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Region;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.entities.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Kyle on 11/21/2015.
 */
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Region findOne(Long id) {
        return regionRepository.findOne(id);
    }

    public Region findByYards(Yard yard){
        return regionRepository.findByYards(yard);
    }

    public void delete(Long id) {
        regionRepository.delete(id);
    }

    public void delete(Region region) {
        regionRepository.delete(region);
    }

    public Region save(Region region) {
        return regionRepository.save(region);
    }
}
