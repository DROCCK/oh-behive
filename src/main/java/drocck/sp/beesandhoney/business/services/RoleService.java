package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Role;
import drocck.sp.beesandhoney.business.entities.User;
import drocck.sp.beesandhoney.business.entities.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rob
 *         Created on 9/30/2015.
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    List<Role> findAll() {
        return roleRepository.findAll();
    }

    List<Role> findByUser(User user) {
        List<Role> roles = findAll();
        List<Role> userRoles = new ArrayList<>();
        return null;
    }

    Role findById(Long id) {
        return roleRepository.findById(id);
    }

    Role save(Role role) {
        return roleRepository.save(role);
    }
}
