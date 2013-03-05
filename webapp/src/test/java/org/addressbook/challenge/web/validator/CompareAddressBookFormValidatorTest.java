package org.addressbook.challenge.web.validator;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.addressbook.challenge.web.form.CompareAddressBookForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

@RunWith(MockitoJUnitRunner.class)
public class CompareAddressBookFormValidatorTest {
    
    private CompareAddressBookFormValidator validator;
    private CompareAddressBookForm compareForm;
    
    @Mock
    private BindingResult bindingResult;
    
    @Before
    public void setup() {
        validator = new CompareAddressBookFormValidator();
        compareForm = new CompareAddressBookForm();
    }
    
    @Test
    public void testSelectedAddressBookEmpty() {
        validator.validate(compareForm, bindingResult);
        verify(bindingResult).reject("addressBook", "compareError");
    }
    
    @Test
    public void testOnlyOneAddressBookDefined() {
        compareForm.setAddressBook(new Long[] {1l});
        validator.validate(compareForm, bindingResult);
        verify(bindingResult).reject("addressBook", "compareError");       
    }
    
    @Test
    public void testThreeAddressBooksDefined() {
        compareForm.setAddressBook(new Long[] {1l, 2l, 3l});
        validator.validate(compareForm, bindingResult);
        verify(bindingResult).reject("addressBook", "compareError");               
    }

    @Test
    public void testCorrectNumberOfAddressBooksDefined() {
        compareForm.setAddressBook(new Long[] {1l, 2l});
        validator.validate(compareForm, bindingResult);
        verify(bindingResult, never()).reject("addressBook", "compareError");               
    }
}
