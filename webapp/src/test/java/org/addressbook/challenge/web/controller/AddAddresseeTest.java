package org.addressbook.challenge.web.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.addressbook.challenge.builder.AddressBookBuilder;
import org.addressbook.challenge.domain.AddressBook;
import org.addressbook.challenge.domain.Addressee;
import org.addressbook.challenge.web.form.NewAddresseeForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddAddresseeTest extends AbstractAddressControllerTest {

    private static final String ADDRESSEE_NAME = "homer";
    private static final String ADDRESSEE_PHONE = "123";
    private static final Long ADDRESS_BOOK_ID = 1l;
    
    private AddressBook addressBook;
    
    @Override
    public void setup() {
        super.setup();
        addressBook = AddressBookBuilder.anAddressBook().withId(ADDRESS_BOOK_ID).build();
    }
    
    @Test
    public void testAddAdressee() {
        NewAddresseeForm newAddressee = createNewAddresseeForm();
        when(dao.get(ADDRESS_BOOK_ID)).thenReturn(addressBook);
        String view = controller.addAddressee(newAddressee, map, bindingResult);
        verify(dao).save(addressBook);
        assertThat(view, equalTo("redirect:addressbook/" + ADDRESS_BOOK_ID));
        assertThat(addressBook.getAddressees().size(), equalTo(1));
        verifyAddressee();
    }

    private NewAddresseeForm createNewAddresseeForm() {
        NewAddresseeForm newAddressee = new NewAddresseeForm();
        newAddressee.setAddressBookId(ADDRESS_BOOK_ID);
        newAddressee.setName(ADDRESSEE_NAME);
        newAddressee.setPhone(ADDRESSEE_PHONE);
        return newAddressee;
    }

    private void verifyAddressee() {
        Addressee addressee = addressBook.getAddressees().get(0);
        assertThat(addressee.getName(), equalTo(ADDRESSEE_NAME));
        assertThat(addressee.getPhone(), equalTo(ADDRESSEE_PHONE));
    }
    
    @Test
    public void testAddAddresseeWhenErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);
        String view = controller.addAddressee(new NewAddresseeForm(), map, bindingResult);
        assertThat(view, equalTo("list"));
        assertThat(map.containsKey("errors"), equalTo(true));
    }
    
}
