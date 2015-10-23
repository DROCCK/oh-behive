package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.DropSite;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by David on 10/9/2015.
 */
public interface DropSiteRepository extends JpaRepository<DropSite, Long> {
    DropSite findById(Long id);

 //   DropSite findByDate(java.sql.Date date);
}