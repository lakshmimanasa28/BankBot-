package com.bankbot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bankbot.base.BasePage;

public class HomePage extends BasePage {

    private By logoutLink = By.xpath("//a[normalize-space()='Log out']");
    private By loginBtn = By.name("btnLogin");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickLogout() {
        WebElement element = waitForVisibility(logoutLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public boolean isLoginPageDisplayed() {
        return isDisplayed(loginBtn);
    }
}