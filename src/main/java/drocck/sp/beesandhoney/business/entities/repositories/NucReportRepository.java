package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.NucReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Chai on 2/23/2016.
 */
@Repository
public interface NucReportRepository extends JpaRepository<NucReport,Long> {
}
