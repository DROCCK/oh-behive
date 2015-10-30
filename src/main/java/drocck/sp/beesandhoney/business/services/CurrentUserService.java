package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Role;
import drocck.sp.beesandhoney.security.CurrentUser;

/**
 * @author Rob
 *         Created on 10/4/2015.
 */
public class CurrentUserService {

    public boolean canAccessUser(CurrentUser currentUser, Long id) {

        return currentUser != null
                && (currentUser.getRoles().contains("ADMIN")
                || currentUser.getId().equals(id));
    }
}
