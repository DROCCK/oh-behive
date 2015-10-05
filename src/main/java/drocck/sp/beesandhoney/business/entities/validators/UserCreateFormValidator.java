package drocck.sp.beesandhoney.business.entities.validators;

import drocck.sp.beesandhoney.business.entities.DTOs.UserCreateForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Rob
 *         Created on 10/3/2015.
 */
@Component
public class UserCreateFormValidator
implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
