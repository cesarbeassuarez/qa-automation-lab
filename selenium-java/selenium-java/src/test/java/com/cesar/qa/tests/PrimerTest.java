package com.cesar.qa.tests;

import com.cesar.qa.config.ConfigReader; //    - importar ConfigReader
import com.cesar.qa.config.DriverManager; //    - importar DriverManager

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class PrimerTest {

    @Test
    public void abrirSerenityis() {

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

        // 3) Lee la URL base desde config.properties
        //    Ej: base.url=https://demo.serenity.is
        String baseUrl = ConfigReader.getProperty("base.url");

        // 4) Navega a la URL configurada
        //    (Esto reemplaza el hardcode: driver.get("https://demo.serenity.is/..."))
        driver.get(baseUrl);

        // 5) Validación / evidencia simple: imprimir el título de la página
        //    En esta etapa, el objetivo es confirmar que todo el wiring funciona.
        String titulo = driver.getTitle();
        System.out.println("Título de la página: " + titulo);

        // 6) Cierra el navegador y limpia el driver para la próxima ejecución
        //    (Más adelante esto se moverá a un BaseTest con @AfterMethod)
        DriverManager.quitDriver();

        System.out.println("Test ejecutado correctamente");
    }
}
