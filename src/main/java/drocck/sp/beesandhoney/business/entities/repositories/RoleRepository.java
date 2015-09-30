package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Role;
import drocck.sp.beesandhoney.business.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Rob
 *         Created on 9/30/2015.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAll();
    Role findById(Long id);
    Role save(Role role);
}
