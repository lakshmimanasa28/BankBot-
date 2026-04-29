package com.bankbot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bankbot.base.BasePage;

public class FundTransferPage extends BasePage {

    private By fundTransferLink = By.linkText("Fund Transfer");

    private By payerAccount = By.name("payersaccount");
    private By payeeAccount = By.name("payeeaccount");
    private By amount = By.name("ammount");
    private By description = By.name("desc");
    private By submitBtn = By.name("AccSubmit");

    private By successMsg = By.xpath("//p[@class='heading3']");

    public FundTransferPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToFundTransfer() {
        WebElement element = waitForVisibility(fundTransferLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void transferFund(String fromAccount, String toAccount, String amt, String descText) {
        type(payerAccount, fromAccount);
        type(payeeAccount, toAccount);
        type(amount, amt);
        type(description, descText);
        click(submitBtn);
    }

    public boolean isTransferSuccessful() {
        return getText(successMsg).contains("Fund Transfer Details");
    }
}