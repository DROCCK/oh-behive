package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Rob
 *         Created on 9/30/2015.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findById(Long id);
    // User findByUsername(String username);
    Optional<User> findByUsername(String username);
    User save(User user);
}
