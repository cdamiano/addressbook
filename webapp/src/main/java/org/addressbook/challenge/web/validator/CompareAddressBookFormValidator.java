package org.addressbook.challenge.web.validator;

import org.addressbook.challenge.web.form.CompareAddressBookForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CompareAddressBookFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CompareAddressBookForm.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        CompareAddressBookForm compareForm = (CompareAddressBookForm)obj;
        if (wrongNumberOfAddressBooksDefined(compareForm)) {
            errors.reject("addressBook", "compareError");
        }
    }

    private boolean wrongNumberOfAddressBooksDefined(CompareAddressBookForm compareForm) {
        return compareForm.getAddressBook() == null || compareForm.getAddressBook().length != 2;
    }

}
