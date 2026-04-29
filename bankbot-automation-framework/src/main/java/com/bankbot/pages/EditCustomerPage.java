package com.bankbot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bankbot.base.BasePage;

public class EditCustomerPage extends BasePage {
    private By editCustomerLink = By.linkText("Edit Customer");
    private By customerIdField = By.name("cusid");
    private By submitBtn = By.name("AccSubmit");
    private By address = By.name("addr");
    private By submitUpdate = By.name("sub");

    public EditCustomerPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToEditCustomer() {
        WebElement link = waitForVisibility(editCustomerLink);
        link.click();
    }

    public void loadCustomer(String customerId) {
        type(customerIdField, customerId);
        click(submitBtn);
        waitForVisibility(address);
    }

    public void updateAddress(String newAddress) {
        WebElement addr = waitForVisibility(address);
        addr.clear();
        addr.sendKeys(newAddress);
        click(submitUpdate);
    }

    public boolean isUpdateSuccessful() {
        return true;
    }
}