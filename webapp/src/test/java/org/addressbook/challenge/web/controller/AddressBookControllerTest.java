package org.addressbook.challenge.web.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

@RunWith(MockitoJUnitRunner.class)
public class AddressBookControllerTest extends AbstractAddressControllerTest {

    @Test
    public void testAddressBookEntryPoint() {
        assertThat(controller.list(), equalTo("list"));
    }
    
    @Test
    public void testListAddressBook() {
        ModelMap map = new ModelMap();
        Long addressBookId = Long.valueOf(1);
        String view = controller.list(addressBookId, map);
        verify(dao).get(addressBookId);
        assertThat(view, equalTo("list"));
        assertThat(map.containsKey("selectedAddressBook"), equalTo(true));
    }
    
    @Test
    public void testGetAddressBookCallsDao() {
        controller.getAddressBookList();
        verify(dao).getAll();
    }

}
