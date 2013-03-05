package org.addressbook.challenge.web.controller;

import org.addressbook.challenge.dao.AddressBookDao;
import org.junit.Before;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

public abstract class AbstractAddressControllerTest {
    
    protected AddressBookController controller;
    protected ModelMap map;

    @Mock
    protected BindingResult bindingResult;

    @Mock
    protected AddressBookDao dao;
    
    @Before
    public void setup() {
        controller = new AddressBookController();
        ReflectionTestUtils.setField(controller, "addressBookDao", dao);
        map = new ModelMap();
    }

}
