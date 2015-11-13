package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.DropSite;
import drocck.sp.beesandhoney.business.entities.Inspection;
import drocck.sp.beesandhoney.business.entities.Yard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by David
 * on 10/9/2015.
 */
public interface DropSiteRepository extends JpaRepository<DropSite, Long> {
    List<DropSite> findAllByDropYard(Yard yard);
    DropSite findByInspections(Inspection inspection);
}