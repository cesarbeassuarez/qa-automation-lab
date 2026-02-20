package com.cesar.qa.tests.login;

import com.cesar.qa.config.ConfigReader;
import com.cesar.qa.config.DriverManager;
import com.cesar.qa.base.BaseTest;

import com.cesar.qa.pages.DashboardPage;
import com.cesar.qa.pages.LoginPage;
import com.cesar.qa.data.TestData;

import com.cesar.qa.utils.check;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginNegativeTests extends BaseTest{

    @Test(dataProvider = "credencialesInvalidas", dataProviderClass = TestData.class)
    public void loginInvalido_muestraError(String caseName, String usuario, String password, String mensajeEsperado) {
        LoginPage loginPage = new LoginPage();
        loginPage.loginComo(usuario, password);
        DashboardPage dashboard = new DashboardPage();

        Assert.assertEquals(loginPage.obtenerMensajeError(), mensajeEsperado, caseName + " - Mensaje de error");
        Assert.assertFalse(dashboard.estaVisibleSafe(), caseName + " - El dashboard NO debería aparecer");
    }

}
