package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Contract;
import drocck.sp.beesandhoney.business.entities.Orchard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Robert Wilk
 *         Created on 2/2/2016.
 */
@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
}
