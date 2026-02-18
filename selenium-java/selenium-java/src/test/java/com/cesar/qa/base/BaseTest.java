package com.cesar.qa.base;

import com.cesar.qa.config.ConfigReader;
import com.cesar.qa.config.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverManager.initDriver();
        String baseUrl = ConfigReader.getProperty("base.url");
        DriverManager.getDriver().get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}