package com.bankbot.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bankbot.base.BaseTest;
import com.bankbot.pages.HomePage;
import com.bankbot.pages.LoginPage;
import com.bankbot.utils.ExcelUtil;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        String path = System.getProperty("user.dir") + "/src/test/resources/testdata.xlsx";
        return ExcelUtil.getData(path, "Login");
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expected, String desc) {

        LoginPage login = new LoginPage(getDriver());
        login.login(username, password);

        switch (expected.toLowerCase()) {

            case "success":
                Assert.assertTrue(login.isLoginSuccessful(), "Login should be successful");
                break;

            case "error":
                String errorAlert = login.getAlertText();
                Assert.assertTrue(errorAlert.contains("not valid"));
                login.switchToAlert().accept();
                break;

            case "blank":
                try {
                    String alertText = login.getAlertText();
                    Assert.assertTrue(alertText.contains("User or Password"));
                    login.switchToAlert().accept();
                } catch (Exception e) {
                    Assert.assertTrue(true);
                }
                break;

            default:
                throw new RuntimeException("Invalid expected result");
        }
    }

    @Test
    public void verifyLogoutRedirectsToLoginPage() {

        LoginPage login = new LoginPage(getDriver());
        login.login(config.getUsername(), config.getPassword());

        HomePage home = new HomePage(getDriver());
        home.clickLogout();
        home.switchToAlert().accept();

        Assert.assertTrue(home.isLoginPageDisplayed(),
                "User should be redirected to login page after logout");
    }
}