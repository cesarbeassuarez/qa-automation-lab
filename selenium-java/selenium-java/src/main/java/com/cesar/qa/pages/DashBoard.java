package com.cesar.qa.pages;

import com.cesar.qa.base.BasePage;
import com.cesar.qa.utils.TestLogger;
import org.openqa.selenium.By;

/**
 * Page Object para el Dashboard (después del login)
 */
public class DashboardPage extends BasePage {

    // ═══════════════════════════════════════════════════════════════
    // LOCATORS
    // ═══════════════════════════════════════════════════════════════

    // Elemento que confirma que estamos en el dashboard
    private final By dashboardTitle = By.cssSelector(".s-SidebarBand");
    private final By sidebarMenu = By.cssSelector(".sidebar-menu");
    private final By userDropdown = By.cssSelector(".dropdown-toggle");
    private final By logoutButton = By.cssSelector("a[href*='Account/Signout']");

    // ═══════════════════════════════════════════════════════════════
    // VERIFICACIONES
    // ═══════════════════════════════════════════════════════════════

    /**
     * Verifica si el dashboard está visible (login exitoso)
     */
    public boolean isDashboardVisible() {
        TestLogger.step("Verificando si el dashboard está visible");
        return isDisplayed(sidebarMenu);
    }

    /**
     * Obtiene la URL actual (para verificar redirección)
     */
    public String getCurrentPageUrl() {
        return getCurrentUrl();
    }

    // ═══════════════════════════════════════════════════════════════
    // ACCIONES
    // ═══════════════════════════════════════════════════════════════

    /**
     * Realiza logout
     */
    public void logout() {
        TestLogger.step("Realizando logout");
        click(userDropdown);
        click(logoutButton);
    }
}