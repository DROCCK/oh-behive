package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.DTOs.UserCreateForm;
import drocck.sp.beesandhoney.business.entities.User;
import drocck.sp.beesandhoney.business.entities.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Robert Wilk
 *         Created on 9/30/2015.
 */
@Service
public class UserService {

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

    public User findById(Long id) {
        return userRepository.findById(id);
    }

    public User create(UserCreateForm form) {
        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRoles(form.getRoles());
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        User u = userRepository.findById(user.getId());
        user.getPerson().setId(u.getId());
        user.getPerson().getContactInfo().setId(u.getId());
        user.getPerson().getContactInfo().getAddress().setId(u.getId());
        u.setPerson(user.getPerson());
        u.getPerson().setContactInfo(user.getPerson().getContactInfo());
        u.getPerson().getContactInfo().setAddress(user.getPerson().getContactInfo().getAddress());
        return userRepository.save(user);
    }
}
