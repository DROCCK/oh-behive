package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.NucReport;
import drocck.sp.beesandhoney.business.entities.Yard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Chai on 2/23/2016.
 */
@Repository
public interface NucReportRepository extends JpaRepository<NucReport, Long> {
    NucReport findOneByYard(Yard yard);
    NucReport findOneByYardAndYear(Yard yard, int year);
}
