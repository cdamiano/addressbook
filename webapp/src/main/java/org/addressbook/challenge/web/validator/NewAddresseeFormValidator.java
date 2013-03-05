package org.addressbook.challenge.web.validator;

import org.addressbook.challenge.web.form.NewAddresseeForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class NewAddresseeFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return NewAddresseeForm.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "empty");
    }

}
