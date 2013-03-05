package org.addressbook.challenge.web.validator;

import org.addressbook.challenge.domain.AddressBook;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AddressBookValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return AddressBook.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty");  
    }

}
