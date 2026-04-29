package com.bankbot.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String folderPath = System.getProperty("user.dir") + "/screenshots";
        File folder = new File(folderPath);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String filePath = folderPath + "/" + testName + "_" + System.currentTimeMillis() + ".png";

        try {
            FileUtils.copyFile(source, new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Unable to capture screenshot");
        }

        return filePath;
    }
}