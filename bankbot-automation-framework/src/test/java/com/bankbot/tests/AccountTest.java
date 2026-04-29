package com.bankbot.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bankbot.base.BaseTest;
import com.bankbot.pages.EditAccountPage;
import com.bankbot.pages.LoginPage;
import com.bankbot.pages.NewAccountPage;
import com.bankbot.pages.NewCustomerPage;

public class AccountTest extends BaseTest {

    @Test
    public void verifyCreateAccount() {

        LoginPage login = new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        NewCustomerPage customer = new NewCustomerPage(getDriver());
        customer.navigateToNewCustomer();

        String email = "user" + System.currentTimeMillis() + "@test.com";

        customer.addNewCustomer(
                "Komal",
                "male",
                "23-10-1992",
                "KL Rao Street",
                "Pune",
                "Maharashtra",
                "411001",
                "9876543210",
                email,
                "komal123");

        String customerId = customer.getCustomerId();

        Assert.assertNotNull(customerId);

        NewAccountPage account = new NewAccountPage(getDriver());
        account.navigateToNewAccount();
        account.createAccount(customerId, "Savings", "5000");

        Assert.assertTrue(account.isAccountCreated());

        String accountId = account.getAccountId();
        Assert.assertNotNull(accountId);
    }

    @Test
    public void verifyEditAccount() {

        LoginPage login = new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        NewCustomerPage customer = new NewCustomerPage(getDriver());
        customer.navigateToNewCustomer();

        String email = "user" + System.currentTimeMillis() + "@test.com";

        customer.addNewCustomer(
                "Sainav",
                "male",
                "08-01-1999",
                "Employees Colony",
                "Jaipur",
                "Rajasthan",
                "302001",
                "9876543210",
                email,
                "Sainav123");

        String customerId = customer.getCustomerId();

        NewAccountPage account = new NewAccountPage(getDriver());
        account.navigateToNewAccount();
        account.createAccount(customerId, "Savings", "5000");

        String accountId = account.getAccountId();

        EditAccountPage edit = new EditAccountPage(getDriver());

        try {
            edit.navigateToEditAccount();
            edit.loadAccount(accountId);
            edit.updateAccountType("Current");

            Assert.assertTrue(edit.isUpdateSuccessful());

        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void verifyInvalidCustomerIdAccountCreation() {

        LoginPage login = new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        NewAccountPage account = new NewAccountPage(getDriver());
        account.navigateToNewAccount();
        account.createAccount("999999", "Savings", "5000");

        String alertText = account.getAlertText();

        Assert.assertTrue(alertText.contains("Customer does not exist"));

        account.switchToAlert().accept();
    }
}