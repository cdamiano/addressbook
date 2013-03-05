package org.addressbook.challenge.web.validator;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.addressbook.challenge.domain.AddressBook;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

@RunWith(MockitoJUnitRunner.class)
public class AddressBookValidatorTest {

    private AddressBookValidator validator;
    private AddressBook addressBook;
    
    @Mock private BindingResult errors;

    @Before
    public void setup() {
        validator = new AddressBookValidator();
        addressBook = new AddressBook();
    }

    @Test
    public void testNameNull() {
        validator.validate(addressBook, errors);
        verify(errors).rejectValue("name", "empty", null, null);
    }

    @Test
    public void testNameNotEmpty() {
        addressBook.setName("book1");
        verify(errors, never()).rejectValue(anyString(), anyString(), isA(Object[].class), anyString());
    }
    
    @Test
    public void testNameWhitespace() {
        addressBook.setName("      ");
        validator.validate(addressBook, errors);
        verify(errors).rejectValue("name", "empty", null, null);
    }
    
    @Test
    public void testClass() {
        assertThat(validator.supports(AddressBook.class), equalTo(true));
    }

}
