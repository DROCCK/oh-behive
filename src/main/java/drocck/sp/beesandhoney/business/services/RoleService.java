package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Role;
import drocck.sp.beesandhoney.business.entities.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 10/10/2015.
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findOne(Long id) {
        return roleRepository.findOne(id);
    }

    public void delete(Long id) {
        roleRepository.delete(id);
    }

    public void delete(Role role) {
        roleRepository.delete(role);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
