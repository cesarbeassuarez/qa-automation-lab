package com.cesar.qa.listeners;

import com.cesar.qa.utils.AllureScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        AllureScreenshot.capturarPantalla();
    }
}