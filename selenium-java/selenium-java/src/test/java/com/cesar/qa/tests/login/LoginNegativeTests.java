package com.cesar.qa.tests.login;
// === Infraestructura del framework ===
import com.cesar.qa.base.BaseTest;
import com.cesar.qa.pages.DashboardPage;
import com.cesar.qa.pages.LoginPage;

import com.cesar.qa.data.TestData;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginNegativeTests extends BaseTest{

    @Test(dataProvider = "credencialesInvalidas", dataProviderClass = TestData.class)
    @Description("Verifica que credenciales inválidas muestran mensaje de error")
    @Severity(SeverityLevel.CRITICAL)
    public void loginInvalido_muestraError(String caseName, String usuario, String password, String mensajeEsperado) {
        LoginPage loginPage = new LoginPage();
        loginPage.loginComo(usuario, password);
        DashboardPage dashboard = new DashboardPage();

        Assert.assertEquals(loginPage.obtenerMensajeError(), mensajeEsperado, caseName + " - Mensaje de error");
        Assert.assertFalse(dashboard.estaVisibleSafe(), caseName + " - El dashboard NO debería aparecer");
    }

}
