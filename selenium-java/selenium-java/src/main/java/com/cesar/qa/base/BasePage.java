package com.cesar.qa.base;

import com.cesar.qa.config.ConfigReader;
import com.cesar.qa.config.DriverManager;
import com.cesar.qa.utils.TestLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private int actionDelay;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver,
                Duration.ofSeconds(ConfigReader.getExplicitTimeout()));
        this.actionDelay = ConfigReader.getActionDelay();
    }

    // ═══════════════════════════════════════════════════════════════
    // NAVEGACIÓN
    // ═══════════════════════════════════════════════════════════════

    protected void navigateTo(String url) {
        TestLogger.info("Navegando a: " + url);
        driver.get(url);
        waitForPageLoad();
        applyDelay();
    }

    protected void waitForPageLoad() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }

    // ═══════════════════════════════════════════════════════════════
    // BÚSQUEDA DE ELEMENTOS (con esperas)
    // ═══════════════════════════════════════════════════════════════

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    // ═══════════════════════════════════════════════════════════════
    // ACCIONES BÁSICAS (wrapeadas)
    // ═══════════════════════════════════════════════════════════════

    protected void click(By locator) {
        TestLogger.info("Click en: " + locator.toString());
        waitForClickable(locator).click();
        applyDelay();
    }

    protected void type(By locator, String text) {
        TestLogger.info("Escribiendo en " + locator.toString() + ": " + maskPassword(text));
        WebElement element = waitForVisible(locator);
        element.sendKeys(text);
        applyDelay();
    }

    protected void clearAndType(By locator, String text) {
        TestLogger.info("Limpiando y escribiendo en " + locator.toString());
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
        applyDelay();
    }

    protected String getText(By locator) {
        String text = waitForVisible(locator).getText();
        TestLogger.info("Texto obtenido de " + locator.toString() + ": " + text);
        return text;
    }

    protected String getAttribute(By locator, String attribute) {
        return waitForVisible(locator).getAttribute(attribute);
    }

    // ═══════════════════════════════════════════════════════════════
    // VERIFICACIONES DE ESTADO
    // ═══════════════════════════════════════════════════════════════

    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    protected boolean exists(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    protected boolean isEnabled(By locator) {
        try {
            return driver.findElement(locator).isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    // ACCIONES AVANZADAS
    // ═══════════════════════════════════════════════════════════════

    protected void scrollToElement(By locator) {
        TestLogger.info("Scroll hacia: " + locator.toString());
        WebElement element = waitForVisible(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", element);
        applyDelay();
    }

    protected void safeClick(By locator) {
        TestLogger.info("Safe click en: " + locator.toString());
        try {
            scrollToElement(locator);
            waitForClickable(locator).click();
        } catch (ElementClickInterceptedException | TimeoutException e) {
            TestLogger.warn("Click normal falló, usando JavaScript click");
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        applyDelay();
    }

    protected void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ═══════════════════════════════════════════════════════════════
    // HELPERS INTERNOS
    // ═══════════════════════════════════════════════════════════════

    private void applyDelay() {
        if (actionDelay > 0) {
            try {
                Thread.sleep(actionDelay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private String maskPassword(String text) {
        if (text != null && text.length() > 0) {
            return "********";
        }
        return text;
    }

    public void setActionDelay(int milliseconds) {
        TestLogger.info("Cambiando delay a: " + milliseconds + "ms");
        this.actionDelay = milliseconds;
    }
}