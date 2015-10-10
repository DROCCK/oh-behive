package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Dropsite;
import drocck.sp.beesandhoney.business.entities.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by cjeli_000 on 10/9/2015.
 */
public interface InspectionRepository extends JpaRepository<Inspection, Long> {
    Inspection findById(Long id);
    List<Inspection> findByDropsite(Dropsite dropsite);
}
