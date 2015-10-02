package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Yard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Connor on 9/29/2015.
 */

public interface YardRepository extends JpaRepository<Yard, Long> {
    List<Yard> findByName(String name);
    Yard findById(Long id);
}
