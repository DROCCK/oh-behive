package drocck.sp.beesandhoney.security;

import drocck.sp.beesandhoney.business.entities.Role;
import drocck.sp.beesandhoney.business.entities.User;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author Rob
 *         Created on 10/4/2015.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getUsername(), user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRolesAsStrings()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public List<String> getRoles() {
        return Arrays.asList(user.getRolesAsStrings());
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                "} " + super.toString();
    }
}
