package com.cesar.qa.tests;
// estructura
import com.cesar.qa.config.ConfigReader;
import com.cesar.qa.config.DriverManager;
// selenium core
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
// selenium waits
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
// testng
import org.testng.annotations.Test;
// java
import java.time.Duration;

public class PrimerTest {

    @Test
    public void abrirDemoSerenityis() throws InterruptedException {

        // 1) Inicializa el WebDriver según lo que diga config.properties
        //    - browser=chrome
        //    - headless=true/false
        //    - window.maximize=true/false
        //    - timeouts
        //    Nota: Esto evita tener "new ChromeDriver()" en cada test.
        DriverManager.initDriver();

        // 2) Obtiene la instancia del driver creada por DriverManager
        //    (Si todavía no existe, la crea automáticamente).
        WebDriver driver = DriverManager.getDriver();


        Thread.sleep(2000);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Wait<WebDriver> fwait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);


        // 3) Lee la URL base desde config.properties
        //    Ej: base.url=https://demo.serenity.is
        String baseUrl = ConfigReader.getProperty("base.url");

        // 4) Navega a la URL configurada
        //    (Esto reemplaza el hardcode: driver.get("https://demo.serenity.is/..."))
        driver.get(baseUrl);

        // 3) Localiza inputs
        By usuarioInput = By.id("LoginPanel0_Username");
        By passwordInput = By.id("LoginPanel0_Password");
        By loginButton = By.id ("LoginPanel0_LoginButton");


        Thread.sleep(2000);



        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Wait<WebDriver> fwait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);

        //WebElement usuario = fwait.until(d -> d.findElement(usuarioInput));
        WebElement usuario = fwait.until(ExpectedConditions.visibilityOfElementLocated(usuarioInput));
        usuario.clear();
        usuario.sendKeys("admin");


        // 4) Limpia e ingresa usuario
        //WebElement usuario = wait.until(ExpectedConditions.visibilityOfElementLocated(usuarioInput));
        //usuario.clear();
        //usuario.sendKeys("admin");

        // 5) Limpia e ingresa password
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        password.clear();
        password.sendKeys("serenity");

        // 6) Click en iniciar sesión
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

        // (opcional) pequeña validación visual / pausa
        System.out.println("Login ejecutado");

        // 5) Validación / evidencia simple: imprimir el título de la página
        //    En esta etapa, el objetivo es confirmar que todo el wiring funciona.
        String titulo = driver.getTitle();
        System.out.println("Título de la página: " + titulo);

        // 6) Cierra el navegador y limpia el driver para la próxima ejecución
        //    (Más adelante esto se moverá a un BaseTest con @AfterMethod)
        // DriverManager.quitDriver();

        System.out.println("Test ejecutado correctamente");
    }



}
