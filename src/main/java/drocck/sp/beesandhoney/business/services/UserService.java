package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.DTOs.UserCreateForm;
import drocck.sp.beesandhoney.business.entities.User;
import drocck.sp.beesandhoney.business.entities.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Rob
 *         Created on 9/30/2015.
 */
@Service
public class UserService {
// implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userRepository.findById(id));
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    public User create(UserCreateForm form) {
        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        return userRepository.save(user);
    }
/*
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

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("No such user");
        return new CustomUserDetails(user);
    }

    private static class CustomUserDetails
    extends User
    implements UserDetails {

        public CustomUserDetails(User user) {
            super(user);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
*/
}
