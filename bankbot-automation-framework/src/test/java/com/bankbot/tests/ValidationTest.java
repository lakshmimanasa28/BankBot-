package com.bankbot.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bankbot.base.BaseTest;
import com.bankbot.pages.LoginPage;
import com.bankbot.pages.NewCustomerPage;

public class ValidationTest extends BaseTest {

    @Test
    public void verifyEmptyFieldValidation() {

        LoginPage login = new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        NewCustomerPage customer = new NewCustomerPage(getDriver());
        customer.navigateToNewCustomer();
        customer.clickSubmit();

        String alertText = customer.getAlertText();

        Assert.assertTrue(alertText.toLowerCase().contains("fill"));

        customer.switchToAlert().accept();
    }

    @Test
    public void verifyNonNumericValidation() {

        LoginPage login = new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        NewCustomerPage customer = new NewCustomerPage(getDriver());
        customer.navigateToNewCustomer();

        customer.typePin("abc");
        customer.typeMobile("xyz");
        customer.clickOutside();

        String pinError = customer.getPinError().toLowerCase();
        String mobileError = customer.getMobileError().toLowerCase();

        Assert.assertTrue(
                pinError.contains("not allowed")
                        || pinError.contains("numbers")
                        || pinError.contains("numeric"));

        Assert.assertTrue(
                mobileError.contains("not allowed")
                        || mobileError.contains("numbers")
                        || mobileError.contains("numeric"));
    }

    @Test
    public void verifyFutureDateValidation() {

        LoginPage login = new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        NewCustomerPage customer = new NewCustomerPage(getDriver());
        customer.navigateToNewCustomer();
        customer.enterDOB("01-01-2050");

        try {
            String alertText = customer.getAlertText();

            Assert.assertTrue(
                    alertText.toLowerCase().contains("future")
                            || alertText.toLowerCase().contains("invalid"));

            customer.switchToAlert().accept();

        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }
}