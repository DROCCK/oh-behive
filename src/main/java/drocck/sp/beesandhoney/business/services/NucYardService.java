package drocck.sp.beesandhoney.business.services;


import drocck.sp.beesandhoney.business.entities.NucYard;
import drocck.sp.beesandhoney.business.entities.repositories.NucYardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by Chai on 2/23/2016.
 */
public class NucYardService {

    @Autowired
    private NucYardRepository nucYardRepository;

    public void delete(NucYard nucYard) {
        nucYardRepository.delete(nucYard);
    }

    public void delete(Long id) {
        nucYardRepository.delete(id);
    }

    public NucYard save(NucYard nucYard) {
        return nucYardRepository.save(nucYard);
    }

    public NucYard update(NucYard nucYard) {
        return nucYardRepository.save(nucYard);
    }

    public NucYard findOne(Long id) {
        return nucYardRepository.findOne(id);
    }

    public List<NucYard> findAll(){
        return nucYardRepository.findAll();
    }
}

