package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Orchard;
import drocck.sp.beesandhoney.business.entities.repositories.OrchardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 2/2/2016.
 */
@Service
public class OrchardService {

    @Autowired
    OrchardRepository orchardRepository;

    public Orchard findOne(Long id) {
        return orchardRepository.findOne(id);
    }

    public List<Orchard> findAll() {
        return orchardRepository.findAll();
    }

    public Orchard save(Orchard orchard) {
        return orchardRepository.save(orchard);
    }

    public void delete(Orchard orchard) {
        orchardRepository.delete(orchard);
    }

    public void delete(Long id) {
        orchardRepository.delete(id);
    }
}
