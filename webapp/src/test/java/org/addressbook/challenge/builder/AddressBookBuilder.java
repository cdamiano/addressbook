package org.addressbook.challenge.builder;

import java.util.ArrayList;
import java.util.List;

import org.addressbook.challenge.domain.AddressBook;
import org.addressbook.challenge.domain.Addressee;
import org.springframework.test.util.ReflectionTestUtils;

public class AddressBookBuilder {

    private String name;
    private List<Addressee> addressees = new ArrayList<Addressee>();
    private Long id;

    public static AddressBookBuilder anAddressBook() {
        return new AddressBookBuilder();
    }
    
    public AddressBookBuilder withId(Long id) {
        this.id = id;
        return this;
    }
    
    public AddressBookBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public AddressBookBuilder withAddressee(Addressee addressee) {
        this.addressees.add(addressee);
        return this;
    }

    public AddressBook build() {
        AddressBook addressBook = new AddressBook();
        ReflectionTestUtils.setField(addressBook, "addressId", this.id);
        addressBook.setName(this.name);
        addressBook.setAddressees(new ArrayList<Addressee>());
        for (Addressee addressee : addressees) {
            addressBook.addAddressee(addressee);
        }
        return addressBook;
    }

}
