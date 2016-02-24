package drocck.sp.beesandhoney.business.services;


import drocck.sp.beesandhoney.business.entities.NucYard;
import drocck.sp.beesandhoney.business.entities.repositories.NucingYardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by Chai on 2/23/2016.
 */
public class NucYardService {

    @Autowired
    private NucingYardRepository nucingYardRepository;

    public void delete(NucYard nucYard) {
        nucingYardRepository.delete(nucYard);
    }

    public void delete(Long id) {
        nucingYardRepository.delete(id);
    }

    public NucYard save(NucYard nucYard) {
        return nucingYardRepository.save(nucYard);
    }

    public NucYard update(NucYard nucYard) {
        return nucingYardRepository.save(nucYard);
    }

    public NucYard findOne(Long id) {
        return nucingYardRepository.findOne(id);
    }

    public List<NucYard> findAll(){
        return nucingYardRepository.findAll();
    }

}
