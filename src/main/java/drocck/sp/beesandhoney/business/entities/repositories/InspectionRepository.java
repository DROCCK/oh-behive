package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Inspection;
import drocck.sp.beesandhoney.business.entities.Yard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

/**
 * Created by Connor on 10/9/2015.
 */
public interface InspectionRepository extends JpaRepository<Inspection, Long> {
    List<Inspection> findAllByYard(Yard yard);
    List<Inspection> findAllByYardAndVisitDateBetween(Yard yard, Date d1, Date d2);
}
