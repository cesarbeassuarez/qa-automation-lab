package com.cesar.qa.pages;

import com.cesar.qa.base.BasePage;
import com.cesar.qa.utils.TestLogger;
import org.openqa.selenium.By;

/**
 * Page Object para la página de Login de demo.serenity.is
 *
 * Responsabilidad: conocer los selectores y acciones de ESTA página.
 * NO contiene lógica de validación (eso va en los tests).
 */
public class LoginPage extends BasePage {

    // ═══════════════════════════════════════════════════════════════
    // LOCATORS (selectores)
    // ═══════════════════════════════════════════════════════════════

    private final By usernameField = By.cssSelector("input[name='Username']");
    private final By passwordField = By.cssSelector("input[name='Password']");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By errorMessage = By.cssSelector(".toast-message");
    private final By forgotPasswordLink = By.linkText("¿Olvidaste tu contraseña?");

    // ═══════════════════════════════════════════════════════════════
    // ACCIONES
    // ═══════════════════════════════════════════════════════════════

    /**
     * Ingresa el nombre de usuario
     */
    public LoginPage enterUsername(String username) {
        TestLogger.step("Ingresando usuario: " + username);
        clearAndType(usernameField, username);
        return this;  // Fluent pattern: permite encadenar
    }

    /**
     * Ingresa la contraseña
     */
    public LoginPage enterPassword(String password) {
        TestLogger.step("Ingresando contraseña");
        clearAndType(passwordField, password);
        return this;
    }

    /**
     * Hace click en el botón de login
     */
    public void clickLogin() {
        TestLogger.step("Click en botón Login");
        click(loginButton);
    }

    /**
     * Flujo completo de login (username + password + click)
     */
    public void login(String username, String password) {
        TestLogger.step("Realizando login con usuario: " + username);
        enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }

    // ═══════════════════════════════════════════════════════════════
    // LECTURAS / VERIFICACIONES
    // ═══════════════════════════════════════════════════════════════

    /**
     * Obtiene el mensaje de error (si existe)
     */
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    /**
     * Verifica si el mensaje de error está visible
     */
    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

    /**
     * Verifica si el campo de usuario está visible
     */
    public boolean isUsernameFieldDisplayed() {
        return isDisplayed(usernameField);
    }
}