package com.cesar.qa.tests.login;

import com.cesar.qa.config.ConfigReader;
import com.cesar.qa.config.DriverManager;

import com.cesar.qa.pages.DashboardPage;
import com.cesar.qa.pages.LoginPage;

import com.cesar.qa.utils.check;

public class LoginNegativeTests {
    public void loginInvalido_passwordIncorrecta_muestraError() {
        DriverManager.initDriver();
        String baseUrl = ConfigReader.getProperty("base.url");
        DriverManager.getDriver().get(baseUrl);

        LoginPage loginPage = new LoginPage();
        loginPage.loginComo("admin", "mal");
        DashboardPage dashboard = new DashboardPage();

        check.equals(loginPage.obtenerMensajeError(),"Error de validación: ¡Nombre de usuario o contraseña inválidos!", "Mensaje de error");
        check.isFalse(dashboard.estaVisibleSafe(), "El dashboard NO debería aparecer");

        DriverManager.quitDriver();
    }
    public void loginInvalido_usuarioIncorrecto_muestraError() {
        DriverManager.initDriver();
        String baseUrl = ConfigReader.getProperty("base.url");
        DriverManager.getDriver().get(baseUrl);

        LoginPage loginPage = new LoginPage();
        loginPage.loginComo("mal", "serenity");
        DashboardPage dashboard = new DashboardPage();

        check.equals(loginPage.obtenerMensajeError(),"Error de validación: ¡Nombre de usuario o contraseña inválidos!", "Mensaje de error");
        check.isFalse(dashboard.estaVisibleSafe(), "El dashboard NO debería aparecer");

        DriverManager.quitDriver();
    }
    public void loginInvalido_tipearEspacios() {
        DriverManager.initDriver();
        String baseUrl = ConfigReader.getProperty("base.url");
        DriverManager.getDriver().get(baseUrl);

        LoginPage loginPage = new LoginPage();
        loginPage.loginComo(" ", " ");
        DashboardPage dashboard = new DashboardPage();

        check.equals(loginPage.obtenerMensajeError(),"Por favor, valide los campos vacíos o inválidos (marcados en rojo) antes de enviar el formulario", "Mensaje de error");
        check.isFalse(dashboard.estaVisibleSafe(), "El dashboard NO debería aparecer");

        DriverManager.quitDriver();
    }

}
