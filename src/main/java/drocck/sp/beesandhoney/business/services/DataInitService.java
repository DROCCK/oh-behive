package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Role;
import drocck.sp.beesandhoney.business.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author Robert Wilk
 *         Created on 11/7/2015.
 */
@Service
public class DataInitService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public void init() {

        if (!(userService.getUserByUsername("admin@admin.com").isPresent())) {
            Role role;
            role = roleService.findOne((long)1);

            if (role == null) {
                role =new Role();
                role.setName("ADMIN");
                roleService.save(role);

            }
            User user = new User();
            user.setUsername("admin@admin.com");
            String passHash = new BCryptPasswordEncoder().encode("password");
            System.out.println("PASSWORD BCRYPT HASH = " + passHash);
            user.setPassword(passHash);
            user.setRoles(Collections.singletonList(role));
            userService.save(user);
        }
    }
}
