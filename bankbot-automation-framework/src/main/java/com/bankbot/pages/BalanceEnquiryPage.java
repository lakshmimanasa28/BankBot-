package com.bankbot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.bankbot.base.BasePage;

public class BalanceEnquiryPage extends BasePage {

    private By balanceLink = By.linkText("Balance Enquiry");
    private By accountNo = By.name("accountno");
    private By submitBtn = By.name("AccSubmit");

    private By balanceValue = By.xpath("//td[text()='Balance']/following-sibling::td");

    public BalanceEnquiryPage(WebDriver driver) {
        super(driver);
    }
    public void navigateToBalance() {
        driver.navigate().to("https://demo.guru99.com/V4/manager/BalEnqInput.php");
    }

   

    public String getBalance(String accountId) {
        type(accountNo, accountId);
        click(submitBtn);
        return getText(balanceValue);
    }
}