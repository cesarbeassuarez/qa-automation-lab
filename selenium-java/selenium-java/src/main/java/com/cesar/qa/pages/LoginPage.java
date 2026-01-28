package com.cesar.qa.pages;
// === Infraestructura del framework ===
import com.cesar.qa.config.DriverManager;

// === Selenium: localizadores y elementos ===
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// === Selenium: esperas explícitas ===
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

// === Java ===
import java.time.Duration;

public class LoginPage {

    // Locators de la página de login
    private final By usernameInput = By.id("LoginPanel0_Username"); // input username
    private final By passwordInput = By.id("LoginPanel0_Password"); // input password
    private final By passwordIncorrect = By.cssSelector("a[href='/Account/ForgotPassword']"); // olvidaste tu contraseña?

    private final By loginButton = By.id("LoginPanel0_LoginButton"); // btn iniciar sesion
    private final By loginGoogle = By.cssSelector("button[type='submit'][value='Google']"); // iniciar sesion con Google
    private final By loginGithub = By.cssSelector("button[type='submit'][value='GitHub']"); // iniciar sesion con Github
    private final By loginMicrosoft = By.cssSelector("button[type='submit'][value='Microsoft']"); // iniciar sesion con Microsoft

    private final By enlaceRegistrarse = By.cssSelector("a[href='/Account/SignUp']"); // Aun no tienes cuenta, registrate

    private final By enlacePlayStore = By.cssSelector("a[class='play-store-link']"); // enlace play store
    private final By enlaceAppStore = By.cssSelector("a[class='app-store-link']"); // enlace App store

    private final By errorMessage = By.cssSelector(".toast-message"); // panel de error que aparece luego de ingresar mal credenciales de inicio

    // Wait (usa el driver del DriverManager)
    private final WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));

    // Ingresar usuario
    public void enterUsername(String username) {
        WebElement usuarioInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        usuarioInputElement.clear();
        usuarioInputElement.sendKeys(username);
    }

    // Ingresar contraseña
    public void enterPassword(String password) {
        WebElement passwordInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passwordInputElement.clear();
        passwordInputElement.sendKeys(password);
    }

    // Hacer clic en botón de login
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    // Fn LOGIN
    public void loginComo(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // msj error
    public String obtenerMensajeError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }


}





