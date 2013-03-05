package org.addressbook.challenge;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.addressbook.challenge.pageobjects.AddressBookHomePage;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddAddresseeSteps {
    
    @Autowired
    WebDriver driver;
    
    private AddressBookHomePage homePage;
    private String name;
    private String phone;
    
    @Given("I am on the home page")
    public void givenIAmOnTheHomePage() {
        homePage = AddressBookHomePage.navigateTo(driver);
        assertThat(driver.getTitle(), equalTo("Address Book"));
    }

    @When("I enter the name as $name")
    public void enterName(String name) {
        this.name = name;
        homePage.enterName(name);
    }
    
    @When("I enter the phone as $phone")
    public void enterPhone(String phone) {
        this.phone = phone;
        homePage.enterPhone(phone);
    }
    
    @When("click add")
    public void whenClickAdd() {
        homePage.clickAdd();
    }

    @Then("the friend is added to the address book")
    public void thenTheFriendIsAddedToTheAddressBook() {
        assertThat(homePage.friendExists(name, phone), is(equalTo((true))));
    }
}
