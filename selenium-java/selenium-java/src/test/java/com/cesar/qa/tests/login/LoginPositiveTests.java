package com.cesar.qa.tests.login;

import com.cesar.qa.config.ConfigReader;
import com.cesar.qa.config.DriverManager;

import com.cesar.qa.utils.check;

import com.cesar.qa.pages.DashboardPage;
import com.cesar.qa.pages.LoginPage;

public class LoginPositiveTests {
    public void loginValido_deberiaIngresar() {
        DriverManager.initDriver();
        String baseUrl = ConfigReader.getProperty("base.url");
        DriverManager.getDriver().get(baseUrl);

        LoginPage loginPage = new LoginPage();
        loginPage.loginComo("admin", "serenity");

        DashboardPage dashboard = new DashboardPage();

        check.visible(dashboard.estaVisible(), "Dashboard visible luego de login válido");
        check.equals(dashboard.obtenerTitulo(), "Tablero", "Título del dashboard");

        DriverManager.quitDriver();

    }
    // Click sin escribir nada (por defecto vienen credenciales correctas tipeadas)
    public void clickLoginSinTipear() {
        DriverManager.initDriver();
        String baseUrl = ConfigReader.getProperty("base.url");
        DriverManager.getDriver().get(baseUrl);

        LoginPage loginPage = new LoginPage();
        loginPage.clickLogin();

        DashboardPage dashboard = new DashboardPage();

        check.visible(dashboard.estaVisible(),"Dashboard visible luego de login válido");
        check.equals(dashboard.obtenerTitulo(), "Tablero", "Título del dashboard");

        DriverManager.quitDriver();
    }

}
