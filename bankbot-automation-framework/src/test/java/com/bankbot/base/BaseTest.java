package com.bankbot.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.bankbot.utils.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected ConfigReader config;

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setUp(ITestContext context) {

        config = new ConfigReader();
        String browser = config.getBrowser().toLowerCase();

        WebDriver localDriver;

        switch (browser) {

            case "chrome":
                WebDriverManager.chromedriver().setup();
                localDriver = new ChromeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                localDriver = new FirefoxDriver();
                break;

            default:
                throw new RuntimeException("Invalid browser: " + browser);
        }

        driver.set(localDriver);
        context.setAttribute("driver", localDriver);

        localDriver.manage().window().maximize();
        localDriver.manage().deleteAllCookies();
        localDriver.get(config.getBaseUrl());
    }

    @AfterMethod
    public void tearDown() {

        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}