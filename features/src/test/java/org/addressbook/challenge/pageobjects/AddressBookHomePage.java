package org.addressbook.challenge.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddressBookHomePage {
    
    @FindBy(name="name")
    private WebElement nameField;
    
    @FindBy(name="phone")
    private WebElement phoneField;
    
    @FindBy(name="add")
    private WebElement addButton;
    
    
    public static AddressBookHomePage navigateTo(WebDriver driver) {
        driver.get("http://localhost:8080");
        return PageFactory.initElements(driver, AddressBookHomePage.class);
    }
    
    public void enterName(String name) {
        nameField.sendKeys(name);
    }
    
    public void enterPhone(String phone) {
        phoneField.sendKeys(phone);
    }
    
    public void clickAdd() {
        addButton.click();
    }

    public boolean friendExists(String name, String phone) {
        return true;
    }


}
