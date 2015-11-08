package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.DropSite;
import drocck.sp.beesandhoney.business.entities.Yard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by David on 10/9/2015.
 */
public interface DropSiteRepository extends JpaRepository<DropSite, Long> {
    DropSite findById(Long id);

    List<DropSite> findAllByDropYard(Yard yard);

 //   DropSite findByDate(java.sql.Date date);
}