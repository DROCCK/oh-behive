package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.entities.repositories.YardRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Connor on 9/26/2015.
 */
@Service
public class YardService {

    @Autowired
    private YardRepository yardRepository;

    @Autowired
    AddressService addressService;

    public YardService() {
        super();
    }

    public List<Yard> findAll() {
        return this.yardRepository.findAll();
    }

    public List<Yard> findAllInUse() {
        List<Yard> yards = new ArrayList<>();
        for (Yard yard : findAll())
            if (yard.getStatus().equals("IN USE"))
                yards.add(yard);
        return yards;
    }

    public Yard findById(Long id) {
        Yard person = yardRepository.findById(id);
        return person;
    }

    public void deleteInBatch(Iterable<Yard> entities) {
        yardRepository.deleteInBatch(entities);
    }

    public Yard save(final Yard yard) {
        addressService.save(yard.getAddress());
        return this.yardRepository.save(yard);
    }
}
