package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.User;
import drocck.sp.beesandhoney.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Rob
 *         Created on 10/4/2015.
 */
public class CurrentUserDetailsService
implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public CurrentUser loadUserByUsername(String username)
        throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return new CurrentUser(user);
    }
}
