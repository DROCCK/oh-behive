package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.security.CurrentUser;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author Rob
 *         Created on 10/4/2015.
 */
@ControllerAdvice
public class CurrentUserControllerAdvice {

    public CurrentUser getCurrentUser(Authentication authentication) {
        return (authentication == null) ?
                null : (CurrentUser) authentication.getPrincipal();
    }
}
