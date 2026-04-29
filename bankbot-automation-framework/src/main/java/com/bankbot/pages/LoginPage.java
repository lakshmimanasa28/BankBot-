package com.bankbot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.bankbot.base.BasePage;

public class LoginPage extends BasePage {
    private By username = By.name("uid");
    private By password = By.name("password");
    private By loginBtn = By.name("btnLogin");
    private By errorMsg = By.id("message23");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String user) {
        type(username, user);
    }

    public void enterPassword(String pass) {
        type(password, pass);
    }

    public void clickLogin() {
        click(loginBtn);
    }

    public void login(String user, String pass) {
        driver.navigate().to(config.getBaseUrl());
        type(username, user);
        type(password, pass);
        click(loginBtn);
    }

    
    public boolean isLoginSuccessful() {
        try {
            return driver.getCurrentUrl().contains("Managerhomepage")
                    || driver.getPageSource().contains("Manager Id")
                    || driver.getTitle().toLowerCase().contains("guru99");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMsg);
    }
}