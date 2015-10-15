package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 10/10/2015.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAll();
    Role findById(Long id);
}
