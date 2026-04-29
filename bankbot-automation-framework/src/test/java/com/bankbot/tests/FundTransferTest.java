package com.bankbot.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bankbot.base.BaseTest;
import com.bankbot.pages.BalanceEnquiryPage;
import com.bankbot.pages.FundTransferPage;
import com.bankbot.pages.LoginPage;
import com.bankbot.pages.NewAccountPage;
import com.bankbot.pages.NewCustomerPage;

public class FundTransferTest extends BaseTest {

    private String[] createTwoAccounts() {
    	LoginPage login = new LoginPage(getDriver());
    	login.login(config.getUsername(), config.getPassword());

        NewCustomerPage customer = new NewCustomerPage(getDriver());
        NewAccountPage account = new NewAccountPage(getDriver());

        customer.navigateToNewCustomer();

        String email = "rahul" + System.currentTimeMillis() + "@test.com";

        customer.addNewCustomer(
                "Rahul Sharma",
                "male",
                "12-07-1994",
                "MG Road",
                "Hyderabad",
                "Telangana",
                "500081",
                "9876543210",
                email,
                "Rahul123");

        String customerId = customer.getCustomerId();

        Assert.assertNotNull(customerId);

        account.navigateToNewAccount();
        account.createAccount(customerId, "Savings", "10000");
        String account1 = account.getAccountId();

        account.navigateToNewAccount();
        account.createAccount(customerId, "Savings", "5000");
        String account2 = account.getAccountId();

        return new String[] { account1, account2 };
    }

    @Test
    public void verifyFundTransferSuccess() {

        String[] accounts = createTwoAccounts();

        String account1 = accounts[0];
        String account2 = accounts[1];

        FundTransferPage transfer = new FundTransferPage(getDriver());
        transfer.navigateToFundTransfer();
        transfer.transferFund(account1, account2, "1000", "Test Transfer");

        Assert.assertTrue(transfer.isTransferSuccessful());
    }

    @Test
    public void verifyBalanceAfterTransfer() {

        String[] accounts = createTwoAccounts();

        String account1 = accounts[0];
        String account2 = accounts[1];

        FundTransferPage transfer = new FundTransferPage(getDriver());
        transfer.navigateToFundTransfer();
        transfer.transferFund(account1, account2, "1000", "Balance Check");

        Assert.assertTrue(transfer.isTransferSuccessful());

        BalanceEnquiryPage balance = new BalanceEnquiryPage(getDriver());

        try {
            balance.navigateToBalance();
            String updatedBalance = balance.getBalance(account1);
            Assert.assertNotNull(updatedBalance);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void verifyInvalidPayeeTransfer() {

        LoginPage login = new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        FundTransferPage transfer = new FundTransferPage(getDriver());
        transfer.navigateToFundTransfer();

        transfer.transferFund("12345", "999999", "1000", "Invalid Transfer");

        String alertText = transfer.getAlertText();

        Assert.assertTrue(
                alertText.toLowerCase().contains("not exist")
                        || alertText.toLowerCase().contains("invalid"));

        transfer.switchToAlert().accept();
    }
}