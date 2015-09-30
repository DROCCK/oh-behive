package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.User;
import drocck.sp.beesandhoney.business.entities.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rob
 *         Created on 9/30/2015.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        users.forEach(
                u -> u.setRoles(roleService.findByUser(u))
        );
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
