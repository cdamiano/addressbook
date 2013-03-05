package org.addressbook.challenge.web.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.addressbook.challenge.builder.AddressBookBuilder;
import org.addressbook.challenge.web.form.CompareAddressBookForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddressBookCompareTest extends AbstractAddressControllerTest {
    
    private static final Long ADDRESS_BOOK_ONE = 1l;
    private static final Long ADDRESS_BOOK_TWO = 2l;

    @Test
    public void testCompare() {
        CompareAddressBookForm compareForm = createCompareAddressBookForm();
        when(dao.get(ADDRESS_BOOK_ONE)).thenReturn(new AddressBookBuilder().withId(ADDRESS_BOOK_ONE).build());
        when(dao.get(ADDRESS_BOOK_TWO)).thenReturn(new AddressBookBuilder().withId(ADDRESS_BOOK_TWO).build());
        String view = controller.compare(compareForm, bindingResult, map);
        assertThat(view, equalTo("compare"));
        verifyModelObjectAdded();
    }

    private CompareAddressBookForm createCompareAddressBookForm() {
        CompareAddressBookForm compareForm = new CompareAddressBookForm();
        compareForm.setAddressBook(new Long[] {ADDRESS_BOOK_ONE, ADDRESS_BOOK_TWO});
        return compareForm;
    }

    private void verifyModelObjectAdded() {
        assertThat(map.containsKey("uniqueFriends"), equalTo(true));
        assertThat(map.containsKey("bookOne"), equalTo(true));
        assertThat(map.containsKey("bookTwo"), equalTo(true));
    }
    
    @Test
    public void testCompareWhenErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);
        String view = controller.compare(new CompareAddressBookForm(), bindingResult, map);
        assertThat(view, equalTo("list"));
        assertThat(map.containsKey("errors"), equalTo(true));
    }

}
