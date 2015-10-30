package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Yard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Connor on 9/29/2015.
 *
 * Refrence on JPA Repository
 * http://docs.spring.io/spring-data/jpa/docs/1.3.0.RELEASE/reference/html/jpa.repositories.html
 */

public interface YardRepository extends JpaRepository<Yard, Long> {
    List<Yard> findByYardName(String yardName);
    Yard findById(Long id);
}
