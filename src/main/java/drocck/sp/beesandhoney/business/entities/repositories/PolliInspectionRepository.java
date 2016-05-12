package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Orchard;
import drocck.sp.beesandhoney.business.entities.PolliInspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Robert Wilk
 * on 5/9/2016.
 */
@Repository
public interface PolliInspectionRepository extends JpaRepository<PolliInspection, Long> {

    List<PolliInspection> findAllByOrchard(Orchard orchard);
}
