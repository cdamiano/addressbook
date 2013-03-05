package org.addressbook.challenge.web.validator;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.addressbook.challenge.web.form.NewAddresseeForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

@RunWith(MockitoJUnitRunner.class)
public class NewAddresseeFormValidatorTest {

    private NewAddresseeFormValidator validator;
    private NewAddresseeForm newAddresseeForm;

    @Mock
    private BindingResult errors;

    @Before
    public void setup() {
        validator = new NewAddresseeFormValidator();
        newAddresseeForm = new NewAddresseeForm();
    }

    @Test
    public void testNamePhoneNull() {
        validator.validate(newAddresseeForm, errors);
        verify(errors).rejectValue("name", "empty", null, null);
        verify(errors).rejectValue("phone", "empty", null, null);
    }

    @Test
    public void testNamePhoneNotEmpty() {
        newAddresseeForm.setName("homer");
        newAddresseeForm.setPhone("999999999");
        verify(errors, never()).rejectValue(anyString(), anyString(), isA(Object[].class), anyString());
    }

    @Test
    public void testNamePhoneWhitespace() {
        newAddresseeForm.setName("   ");
        newAddresseeForm.setPhone("  ");
        validator.validate(newAddresseeForm, errors);
        verify(errors).rejectValue("name", "empty", null, null);
        verify(errors).rejectValue("phone", "empty", null, null);
    }

    @Test
    public void testClass() {
        assertThat(validator.supports(NewAddresseeForm.class), equalTo(true));
    }
}
