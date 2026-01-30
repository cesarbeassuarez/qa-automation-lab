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
public class DashboardPage {
    // Locators de la página dashboard
    private final By dashboardBody = By.cssSelector("body[id='s-DashboardPage']"); // Dashboard body id
    private final By dashboardHeader = By.cssSelector("section[class='content-header']>h1"); // titulo de dashboard: 'Tablero'

    // Wait (usa el driver del DriverManager)
    private final WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));

    public boolean estaVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardBody)).isDisplayed();
    }

    public String obtenerTitulo() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).getText();
    }

    public boolean estaVisibleSafe() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardBody));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
