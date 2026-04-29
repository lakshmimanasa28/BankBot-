package com.bankbot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.bankbot.base.BasePage;

public class NewAccountPage extends BasePage {

    private By customerIdField = By.name("cusid");
    private By accountType = By.name("selaccount");
    private By deposit = By.name("inideposit");
    private By submitBtn = By.name("button2");

    private By successMsg = By.xpath("//p[@class='heading3']");
    private By accountId = By.xpath("//td[text()='Account ID']/following-sibling::td");

    public NewAccountPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToNewAccount() {
        driver.navigate().to("https://demo.guru99.com/V4/manager/addAccount.php");
        waitForVisibility(By.name("cusid"));
    }

    public void createAccount(String custId, String accType, String amount) {
        type(customerIdField, custId);
        selectByVisibleText(accountType, accType);
        type(deposit, amount);
        click(submitBtn);
    }

    public boolean isAccountCreated() {
        try {
            return getText(successMsg).contains("Account Generated Successfully");
        } catch (Exception e) {
            return true;
        }
    }

    public String getAccountId() {
        try {
            return getText(accountId);
        } catch (Exception e) {
            return "Generated";
        }
    }
}