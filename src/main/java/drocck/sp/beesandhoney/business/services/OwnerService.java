package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Owner;
import drocck.sp.beesandhoney.business.entities.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Oscar on 9/27/2015.
 * Based on yard service
 */
@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    public OwnerService() {super();}

    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    public void save(final Owner newOwner) {
        ownerRepository.save(newOwner);
    }
}
