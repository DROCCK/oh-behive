package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Owner;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar on 9/27/2015.
 * Based on Yard repository
 */
@Repository
public class OwnerRepository {
    private final List<Owner> owners = new ArrayList<Owner>();

    public OwnerRepository() {super();}

    public List<Owner> findAll() {
            return new ArrayList<Owner>(owners);
        }

    public void add(final Owner newOwner) {
            owners.add(newOwner);
        }
}
