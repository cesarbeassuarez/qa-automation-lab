package com.cesar.qa.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class DriverManager {

    // ══════════════════════════════════════════════════════════════
    // ThreadLocal: cada hilo de ejecución tiene su propio driver
    // Esto permite correr tests en paralelo sin conflictos
    // ══════════════════════════════════════════════════════════════
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // ══════════════════════════════════════════════════════════════
    // INIT: Crea e inicializa el WebDriver
    // ══════════════════════════════════════════════════════════════
    public static void initDriver() {
        // Solo crea si no existe uno ya
        if (driverThreadLocal.get() == null) {

            // WebDriverManager descarga chromedriver automáticamente
            // Sin esto, tendrías que descargarlo manual y configurar la ruta
            WebDriverManager.chromedriver().setup();

            // ChromeOptions: configuración del navegador
            ChromeOptions options = new ChromeOptions();

            // Maximizar ventana (si está en config.properties)
            if (ConfigReader.shouldMaximize()) {
                options.addArguments("--start-maximized");
            }

            // Modo headless: sin interfaz gráfica (para CI/CD)
            if (ConfigReader.isHeadless()) {
                options.addArguments("--headless=new");
            }

            // Opciones para estabilidad (evitan errores en algunos sistemas)
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            // Crear el driver con las opciones
            WebDriver driver = new ChromeDriver(options);

            // Timeout implícito: cuánto espera al buscar elementos
            driver.manage().timeouts().implicitlyWait(
                    Duration.ofSeconds(ConfigReader.getInt("timeout.implicit"))
            );

            // Timeout de carga: cuánto espera a que cargue la página
            driver.manage().timeouts().pageLoadTimeout(
                    Duration.ofSeconds(ConfigReader.getInt("timeout.pageload"))
            );

            // Guardar el driver en ThreadLocal
            driverThreadLocal.set(driver);
        }
    }

    // ══════════════════════════════════════════════════════════════
    // GET: Obtiene el driver actual
    // ══════════════════════════════════════════════════════════════
    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();

        // Si no existe, es un error de uso
        if (driver == null) {
            throw new IllegalStateException(
                    "Driver no inicializado. Llamar a initDriver() primero."
            );
        }
        return driver;
    }

    // ══════════════════════════════════════════════════════════════
    // QUIT: Cierra el navegador y limpia el driver
    // ══════════════════════════════════════════════════════════════
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();           // Cierra el navegador
            driverThreadLocal.remove();  // Limpia la referencia
        }
    }
}