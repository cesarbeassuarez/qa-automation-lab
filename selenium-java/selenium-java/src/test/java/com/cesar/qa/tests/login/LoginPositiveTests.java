package com.cesar.qa.tests.login;

// === Infraestructura del framework ===
import com.cesar.qa.base.BaseTest;
import com.cesar.qa.pages.DashboardPage;
import com.cesar.qa.pages.LoginPage;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPositiveTests extends BaseTest{
    @Test
    @Description("Verifica login exitoso con credenciales válidas")
    @Severity(SeverityLevel.CRITICAL)
    public void loginValido_deberiaIngresar() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginComo("admin", "serenity");

        DashboardPage dashboard = new DashboardPage();

        Assert.assertTrue(dashboard.estaVisible(), "Dashboard visible luego de login válido");
        Assert.assertEquals(dashboard.obtenerTitulo(), "Tableros", "Título del dashboard");
    }
    // Click sin escribir nada (por defecto vienen credenciales correctas tipeadas)
    @Test
    @Description("Verifica que click en Login sin credenciales no rompe la app")
    @Severity(SeverityLevel.NORMAL)
    public void clickLoginSinTipear() {
        LoginPage loginPage = new LoginPage();
        loginPage.clickLogin();

        DashboardPage dashboard = new DashboardPage();

        Assert.assertTrue(dashboard.estaVisible(), "Dashboard visible luego de login válido");
        Assert.assertEquals(dashboard.obtenerTitulo(), "Tableros", "Título del dashboard");
    }

}
