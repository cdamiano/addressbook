package org.addressbook.challenge.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.addressbook.challenge.builder.AddressBookBuilder;
import org.addressbook.challenge.builder.AddresseeBuilder;
import org.junit.Test;

public class AddressBookTest {

    @Test
    public void testFindUniqueAddresseesFrom() {
        AddressBook addressBookOne = createAddressBookWithAddressee("Bob", "Mary", "Jane");
        AddressBook addressBookTwo = createAddressBookWithAddressee("Mary", "John", "Jane");
        List<Addressee> result = addressBookOne.findUniqueAddessessFrom(addressBookTwo);
        verifyResult(result);
        result = addressBookTwo.findUniqueAddessessFrom(addressBookOne);
        verifyResult(result);
        addressBookOne.findUniqueAddessessFrom(null);
    }
    
    @Test
    public void testFindUniqueAddresseesFromWhenNull() {
        AddressBook addressBookOne = createAddressBookWithAddressee("Bob", "Mary", "Jane");
        List<Addressee> result = addressBookOne.findUniqueAddessessFrom(null);
        assertThat(result, not(equalTo(null)));
        assertThat(result.size(), equalTo(0));
    }
    
    @Test
    public void testEquals() {
        AddressBook addressBookOne = AddressBookBuilder.anAddressBook().withId(Long.valueOf(1)).build();
        AddressBook addressBookTwo = AddressBookBuilder.anAddressBook().withId(Long.valueOf(1)).build();
        assertThat(addressBookOne, equalTo(addressBookTwo));
        addressBookTwo = AddressBookBuilder.anAddressBook().withId(Long.valueOf(2)).build();
        assertThat(addressBookOne, not(equalTo(addressBookTwo)));
        assertThat(addressBookOne, not(equalTo(null)));
    }

    private void verifyResult(List<Addressee> result) {
        assertThat(result, not(equalTo(null)));
        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0).getName(), equalTo("Bob"));
        assertThat(result.get(1).getName(), equalTo("John"));
    }

    private AddressBook createAddressBookWithAddressee(String... names) {
        AddressBook addressBook = AddressBookBuilder.anAddressBook().build();
        for (String name : names) {
            addressBook.addAddressee(AddresseeBuilder.anAddressee().withName(name).build());
        }
        return addressBook;

    }

}
