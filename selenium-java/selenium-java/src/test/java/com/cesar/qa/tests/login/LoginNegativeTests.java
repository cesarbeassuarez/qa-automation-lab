package com.cesar.qa.tests.login;

import com.cesar.qa.config.DriverManager;
import com.cesar.qa.pages.LoginPage;

public class LoginNegativeTests {
    public void loginInvalido_passwordIncorrecta_muestraError() {
        DriverManager.initDriver();

        LoginPage loginPage = new LoginPage();

        loginPage.loginComo("admin", "mal");

        String mensaje = loginPage.obtenerMensajeError();
        System.out.println("Mensaje de error: " + mensaje);

        DriverManager.quitDriver();
    }

    public void loginInvalido_camposVacios_muestraError() {
        DriverManager.initDriver();

        LoginPage loginPage = new LoginPage();
        loginPage.loginComo("", "");

        String mensaje = loginPage.obtenerMensajeError();
        System.out.println("Mensaje de error: " + mensaje);

        DriverManager.quitDriver();
    }
}
