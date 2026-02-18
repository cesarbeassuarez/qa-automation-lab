package com.cesar.qa.tests.login;

import com.cesar.qa.config.ConfigReader;
import com.cesar.qa.config.DriverManager;
import com.cesar.qa.base.BaseTest;

import com.cesar.qa.utils.check;

import com.cesar.qa.pages.DashboardPage;
import com.cesar.qa.pages.LoginPage;

import org.testng.annotations.Test;

public class LoginPositiveTests extends BaseTest{
    @Test
    public void loginValido_deberiaIngresar() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginComo("admin", "serenity");

        DashboardPage dashboard = new DashboardPage();

        check.visible(dashboard.estaVisible(), "Dashboard visible luego de login válido");
        check.equals(dashboard.obtenerTitulo(), "Tablero", "Título del dashboard");
    }
    // Click sin escribir nada (por defecto vienen credenciales correctas tipeadas)
    @Test
    public void clickLoginSinTipear() {
        LoginPage loginPage = new LoginPage();
        loginPage.clickLogin();

        DashboardPage dashboard = new DashboardPage();

        check.visible(dashboard.estaVisible(),"Dashboard visible luego de login válido");
        check.equals(dashboard.obtenerTitulo(), "Tablero", "Título del dashboard");
    }

}
