package org.addressbook.challenge.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.addressbook.challenge.dao.AddressBookDao;
import org.addressbook.challenge.domain.AddressBook;
import org.addressbook.challenge.domain.Addressee;
import org.addressbook.challenge.web.form.CompareAddressBookForm;
import org.addressbook.challenge.web.form.NewAddresseeForm;
import org.addressbook.challenge.web.validator.AddressBookValidator;
import org.addressbook.challenge.web.validator.CompareAddressBookFormValidator;
import org.addressbook.challenge.web.validator.NewAddresseeFormValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * This controller handles the interaction with the address book front end.
 * <p/>
 * It handles requests for:
 * <ul>
 * <li>creating address books</li>
 * <li>adding addressees to address books</li>
 * <li>comparing address books</li>
 * </ul> 
 * It adds a session attribute of selectedAddressBook to keep track of the currently
 * active address book.  This shall be where the addressee will be added.
 */
@Controller
@SessionAttributes(value={"selectedAddressBook"})
public class AddressBookController {
    
    /**
     * Object to persist the address book data.
     */
    @Resource
    private AddressBookDao addressBookDao;
    
    /**
     * This is the initial entry point for the web app.
     * @return the view to render.
     */
    @RequestMapping(value="/addressbook", method = RequestMethod.GET)
    public String list() {
        return "list";
    }
    
    /**
     * This method looks up the address book based on the id passed to it.
     * @param id the address book id to look up
     * @param map model data where to add the selected address book
     * @return view to render
     */
    @RequestMapping(value="/addressbook/{id}", method = RequestMethod.GET)
    public String list(@PathVariable Long id, ModelMap map) {
        AddressBook addressBook = addressBookDao.get(id);
        map.put("selectedAddressBook", addressBook);
        return "list";
    }    

    /**
     * This method creates a new address book.
     * @param newAddressBook holds the name of the address book
     * @param map errors object will added to object if creation of address book fails validate
     * @param bindingResult report any errors via the validator
     * @return if creation is successful then a redirect to /addressbook/:id shall be return, else list.
     */
    @RequestMapping(value="/addressbook", method = RequestMethod.POST)
    public String createAddressBook(AddressBook newAddressBook, ModelMap map, BindingResult bindingResult) {
        AddressBookValidator validator = new AddressBookValidator();
        validator.validate(newAddressBook, bindingResult);
        if (bindingResult.hasErrors()) {
            map.put("errors", bindingResult);
            return "list";
        }
        addressBookDao.save(newAddressBook);
        return "redirect:addressbook/" + newAddressBook.getId();
    }
    

    /**
     * Add an addressee to the active address book/
     * @param newAddressee holds the form data
     * @param model errors object will added to object if creation of address book fails validate
     * @param bindingResult report any errors via the validator
     * @return if addressee has been added  successfully then a redirect to /addressbook/:id, otherwise list
     */
    @RequestMapping(value="/addAddressee", method = RequestMethod.POST)
    public String addAddressee(@ModelAttribute("newAddressee") NewAddresseeForm newAddressee, ModelMap model, BindingResult bindingResult) {
        NewAddresseeFormValidator validator = new NewAddresseeFormValidator();
        validator.validate(newAddressee, bindingResult);
        if (bindingResult.hasErrors()) {
            model.put("errors", bindingResult);
            return "list";
        }
        AddressBook addressBook = addressBookDao.get(newAddressee.getAddressBookId());
        Addressee addressee = createAddressee(newAddressee);
        addressBook.addAddressee(addressee);
        addressBookDao.save(addressBook);
        return "redirect:addressbook/" + addressBook.getId();
    }

    /**
     * Create an {@link Addressee} object
     * @param newAddressee the data for the {@link Addressee} object
     * @return an {@link Addressee} object
     */
    private Addressee createAddressee(NewAddresseeForm newAddressee) {
        Addressee addressee = new Addressee();
        addressee.setName(newAddressee.getName());
        addressee.setPhone(newAddressee.getPhone());
        return addressee;
    }

    /**
     * Compare to address books and display addressee that are unique to each one.
     * @param compareForm contains the address book to compare
     * @param bindingResult used to validate the data entered by the user
     * @param map errors object will added to object if validate fails, otherwise unique addressees added
     * @return the compare view, if errors then list view
     */
    @RequestMapping(value="/addressbook/compare", method = RequestMethod.GET)
    public String compare(CompareAddressBookForm compareForm, BindingResult bindingResult, ModelMap map) {
        CompareAddressBookFormValidator validator = new CompareAddressBookFormValidator();
        validator.validate(compareForm, bindingResult);
        if (bindingResult.hasErrors()) {
            map.put("errors", bindingResult);
            return "list";
        }
        AddressBook addressBookOne = addressBookDao.get(compareForm.getAddressBook()[0]);
        AddressBook addressBookTwo = addressBookDao.get(compareForm.getAddressBook()[1]);                
        map.put("uniqueFriends", addressBookOne.findUniqueAddessessFrom(addressBookTwo));
        map.put("bookOne", addressBookOne.getName());
        map.put("bookTwo", addressBookTwo.getName());
        
        return "compare";
    }
    
    
    /**
     * List of all the address books.
     * @return  all the address books.
     */
    @ModelAttribute(value="addressBookList")
    public List<AddressBook> getAddressBookList() {
        return addressBookDao.getAll();
    }

    /**
     * A new {@link AddressBook} object used in the form to create an address book
     * @return new {@link AddressBook} object
     */
    @ModelAttribute(value="newAddressBook")
    public AddressBook newAddressBook() {
        return new AddressBook();
    }

    /**
     * A new {@link NewAddresseeForm} object used in the adding of new addressee.
     * @return new {@link NewAddresseeForm} object
     */
    @ModelAttribute(value="newAddressee")
    public NewAddresseeForm newAddressee() {
        return new NewAddresseeForm();
    }
    
    /**
     * A new {@link CompareAddressBookForm} object used in the compare form.
     * @return new {@link CompareAddressBookForm} object
     */
    @ModelAttribute(value="compareAddressBook")
    public CompareAddressBookForm compareAddressBookForm() {
        return new CompareAddressBookForm();
    }

}
