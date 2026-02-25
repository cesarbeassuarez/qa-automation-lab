package com.cesar.qa.pages;
// === Infraestructura del framework ===
import com.cesar.qa.base.BasePage;

// === Selenium: localizadores y elementos ===
import org.openqa.selenium.By;

// === Selenium: esperas explícitas ===
import org.openqa.selenium.support.ui.ExpectedConditions;

// === Java ===

public class DashboardPage extends BasePage { // wait ya viene de BasePage, no hay que declararlo

    // Locators de la página dashboard
    private final By dashboardBody = By.cssSelector("body[id='s-DashboardPage']"); // Dashboard body id
    private final By dashboardHeader = By.cssSelector("section[class='content-header']>h1"); // titulo de dashboard: 'Tablero'
    private final By btnNorthwind = By.cssSelector("a[href='#nav_menu1_2_1']");
    private final By linkClientes = By.cssSelector("a[href='/Northwind/Customer']");

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

    // Método para navegar
    public void irAClientes() {
        wait.until(ExpectedConditions.elementToBeClickable(btnNorthwind)).click();
        wait.until(ExpectedConditions.elementToBeClickable(linkClientes)).click();
    }
}
