package org.addressbook.challenge.dao;

import java.util.List;

import org.addressbook.challenge.domain.AddressBook;

/**
 * A data access object to persist, retrieve {@link AddressBook} objects.
 *
 */
public interface AddressBookDao {

    /**
     * Retrieve all {@link AddressBook} objects defined in the system.
     * @return all {@link AddressBook} objects defined in the system.
     */
    List<AddressBook> getAll();
    /**
     * Persist the {@link AddressBook} object.
     * @param addressBook the object to persist
     */
    void save(AddressBook addressBook);
    /**
     * Retrieve an {@link AddressBook} object based on the id given.
     * @param id the {@link AddressBook} to retrieve
     * @return the retrieved {@link AddressBook} object.
     */
    AddressBook get(Long id);
}
