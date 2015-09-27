package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Yard;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Connor on 9/26/2015.
 */
@Repository
public class YardRepository {
    private final List<Yard> yards = new ArrayList<Yard>();

    public YardRepository() {
        super();
    }

    public List<Yard> findAll() {
        return new ArrayList<Yard>(this.yards);
    }

    public void add(final Yard yard) {
        this.yards.add(yard);
    }
}
