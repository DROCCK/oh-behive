package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.entities.repositories.YardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Connor on 9/26/2015.
 */
@Service
public class YardService {

    @Autowired
    private YardRepository yardRepository;

    public YardService() {
        super();
    }

    public List<Yard> findAll() {
        return this.yardRepository.findAll();
    }

    public void add(final Yard yard) {
        this.yardRepository.add(yard);
    }
}
