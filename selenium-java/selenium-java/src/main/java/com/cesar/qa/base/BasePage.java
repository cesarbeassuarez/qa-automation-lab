package com.cesar.qa.base;

import com.cesar.qa.config.DriverManager;
import com.cesar.qa.config.ConfigReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        int timeout = Integer.parseInt(ConfigReader.getProperty("timeout.explicit"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }
}