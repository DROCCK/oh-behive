package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.DropSite;
import drocck.sp.beesandhoney.business.entities.Owner;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.entities.repositories.YardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Connor
 * on 9/26/2015.
 */
@Service
public class YardService {

    @Autowired
    private YardRepository yardRepository;

    @Autowired
    private AddressService addressService;

    public List<Yard> findAll() {
        return this.yardRepository.findAll();
    }

    public List<Yard> findAllInUse() {
        return findAll().stream().filter(
            yard -> yard.getStatus().equals("IN USE")).collect(Collectors.toList()
        );
    }

    public List<Yard> findAllByOwner(Owner owner) {
        return yardRepository.findAllByOwner(owner);
    }

    public Yard findByDropSite(DropSite dropSite) {
        return yardRepository.findDropYardByDrops(dropSite);
    }

    public Yard findOne(Long id) {
        return yardRepository.findOne(id);
    }

    public void deleteInBatch(Iterable<Yard> entities) {
        yardRepository.deleteInBatch(entities);
    }

    public Yard save(final Yard yard) {
        return yardRepository.save(yard);
    }
}
