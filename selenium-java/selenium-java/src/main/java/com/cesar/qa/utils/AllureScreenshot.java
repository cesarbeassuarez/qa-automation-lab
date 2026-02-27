package com.cesar.qa.utils;

import com.cesar.qa.config.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class AllureScreenshot {

    @Attachment(value = "Screenshot en fallo", type = "image/png")
    public static byte[] capturarPantalla() {
        return ((TakesScreenshot) DriverManager.getDriver())
                .getScreenshotAs(OutputType.BYTES);
    }
}