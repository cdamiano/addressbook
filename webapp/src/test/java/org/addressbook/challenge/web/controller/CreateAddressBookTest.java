package org.addressbook.challenge.web.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.addressbook.challenge.builder.AddressBookBuilder;
import org.addressbook.challenge.domain.AddressBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateAddressBookTest extends AbstractAddressControllerTest {

    private AddressBook newAddressBook;

    @Override
    public void setup() {
        super.setup();
        newAddressBook = AddressBookBuilder.anAddressBook().withName("book1").build();
    }

    @Test
    public void testCreateAddressBook() {
        String view = controller.createAddressBook(newAddressBook, map, bindingResult);
        verify(dao).save(newAddressBook);
        assertThat(view, startsWith("redirect:addressbook/"));
    }

    @Test
    public void testCreateAddressBookWhenErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);
        String view = controller.createAddressBook(newAddressBook, map, bindingResult);
        assertThat(view, equalTo("list"));
        assertThat(map.containsKey("errors"), equalTo(true));
    }

}
