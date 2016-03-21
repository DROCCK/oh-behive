package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Orchard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 2/2/2016.
 */
@Repository
public interface OrchardRepository extends JpaRepository<Orchard, Long> {

    @Query("select o.yardName from Orchard o")
    List<String> findAllOrchardNames();

    Orchard findOneByYardName(String yardName);
}
