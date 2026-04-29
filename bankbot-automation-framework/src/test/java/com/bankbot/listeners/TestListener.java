package com.bankbot.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.bankbot.base.BaseTest;
import com.bankbot.utils.ExtentManager;
import com.bankbot.utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    private ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        WebDriver driver = BaseTest.getDriver();

        String screenshotPath =
                ScreenshotUtil.captureScreenshot(driver, result.getName());

        try {
            test.get().addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            test.get().fail("Unable to attach screenshot");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}