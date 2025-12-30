import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class PrimerTest {

    @Test
    public void abrirGoogle() {
        // Setup automático del driver
        WebDriverManager.chromedriver().setup();

        // Crear instancia del driver
        WebDriver driver = new ChromeDriver();

        // Abrir Google
        driver.get("https://www.google.com");

        // Obtener título
        String titulo = driver.getTitle();
        System.out.println("Título de la página: " + titulo);

        // Cerrar browser
        driver.quit();

        System.out.println("Test ejecutado correctamente");
    }
}
