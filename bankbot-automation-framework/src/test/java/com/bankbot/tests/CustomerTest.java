package com.bankbot.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bankbot.base.BaseTest;
import com.bankbot.pages.EditCustomerPage;
import com.bankbot.pages.LoginPage;
import com.bankbot.pages.NewCustomerPage;

public class CustomerTest extends BaseTest {

    @Test
    public void verifyCreateCustomer() {
        LoginPage login = new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        NewCustomerPage customer = new NewCustomerPage(getDriver());
        customer.navigateToNewCustomer();

        String email = "Manasa" + System.currentTimeMillis() + "@test.com";
        customer.addNewCustomer(
                "Manasa",
                "female",
                "10-11-2004",
                "Koritipadu",
                "Guntur",
                "Andhra Pradesh",
                "522007",
                "6305784246",
                email,
                "Password123"
        );

        Assert.assertTrue(customer.isCustomerCreated(), "Customer should be created successfully");

        String custId = customer.getCustomerId();
        Assert.assertNotNull(custId, "Customer ID should be generated");
        Assert.assertFalse(custId.trim().isEmpty(), "Customer ID should not be empty");
    }

    @Test
    public void verifyDuplicateEmailError() {
        LoginPage login = new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        NewCustomerPage customer = new NewCustomerPage(getDriver());
        customer.navigateToNewCustomer();

        String email = "user" + System.currentTimeMillis() + "@test.com";

        customer.addNewCustomer(
                "San",
                "male",
                "01-01-1995",
                "Tikle Road",
                "Hyderabad",
                "Telangana",
                "500001",
                "9876543210",
                email,
                "San123"
        );

        customer.navigateToNewCustomer();

        customer.addNewCustomer(
                "Aish",
                "female",
                "04-06-1995",
                "Kolan Street",
                "Bangalore",
                "Karnataka",
                "560001",
                "9876543210",
                email,
                "Aish123"
        );

        String alertText = customer.getAlertText();
        Assert.assertTrue(alertText.contains("Email Address Already Exist")
                || alertText.toLowerCase().contains("already exist"));

        customer.switchToAlert().accept();
    }

    @Test
    public void verifyEditCustomer() {
        LoginPage login = new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        NewCustomerPage customer = new NewCustomerPage(getDriver());
        customer.navigateToNewCustomer();

        String email = "haindhav" + System.currentTimeMillis() + "@test.com";
        customer.addNewCustomer(
                "Haindhav",
                "male",
                "01-08-2001",
                "Sanikya Colony",
                "Chennai",
                "Tamil Nadu",
                "600001",
                "9876543210",
                email,
                "Haindhav123"
        );

        String custId = customer.getCustomerId();
        Assert.assertNotNull(custId, "Customer ID should be generated");

        login.login(config.getUsername(), config.getPassword());

        EditCustomerPage edit = new EditCustomerPage(getDriver());
        edit.navigateToEditCustomer();
        edit.loadCustomer(custId);
        edit.updateAddress("Updated Address");

        Assert.assertTrue(edit.isUpdateSuccessful(), "Customer should be updated");
    }
}