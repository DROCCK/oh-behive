package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Address;
import drocck.sp.beesandhoney.business.entities.Drop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by David on 10/9/2015.
 */
public interface DropRepository extends JpaRepository<Drop, Long> {
    List<Drop> findAll();
    Drop findById(Long id);
    Drop findByDate(java.sql.Date date);
    Drop save(Drop drop);
    void delete(Long id);
    void delete(Drop drop);
}