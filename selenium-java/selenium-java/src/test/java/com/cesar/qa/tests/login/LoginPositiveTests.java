package com.cesar.qa.tests.login;

import com.cesar.qa.config.DriverManager;
import com.cesar.qa.pages.LoginPage;

public class LoginPositiveTests {
    public void loginValido_deberiaIngresar() {
        DriverManager.initDriver();

        LoginPage loginPage = new LoginPage();

        loginPage.loginComo("admin", "admin");

        DriverManager.quitDriver();
    }
}
