package com.cesar.qa.tests;

import com.cesar.qa.config.ConfigReader;
import com.cesar.qa.config.DriverManager;
import com.cesar.qa.pages.DashboardPage;
import com.cesar.qa.pages.LoginPage;
import com.cesar.qa.utils.TestLogger;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

/**
 * Tests de Login para demo.serenity.is
 *
 * Demuestra:
 * - Hard assertions (detienen el test)
 * - Soft assertions (continúan y reportan al final)
 * - Uso de Page Objects
 * - Logging de cada paso
 */
public class LoginTests {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    // Datos de test desde config.properties
    private String baseUrl;
    private String validUsername;
    private String validPassword;

    @BeforeClass
    public void setupClass() {
        TestLogger.info("═══════════════════════════════════════════════════════");
        TestLogger.info("INICIANDO SUITE: Login Tests");
        TestLogger.info("═══════════════════════════════════════════════════════");

        // Cargar configuración
        baseUrl = ConfigReader.getBaseUrl();
        validUsername = ConfigReader.getUsername();
        validPassword = ConfigReader.getPassword();
    }

    @BeforeMethod
    public void setupMethod() {
        // Inicializar driver fresco para cada test
        DriverManager.initDriver();

        // Inicializar Page Objects
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();

        // Navegar a la página de login
        DriverManager.getDriver().get(baseUrl);
    }

    @AfterMethod
    public void teardownMethod() {
        // Cerrar browser después de cada test
        DriverManager.quitDriver();
    }

    // ═══════════════════════════════════════════════════════════════
    // TESTS
    // ═══════════════════════════════════════════════════════════════

    /**
     * Test 1: Login exitoso con credenciales válidas
     *
     * HARD ASSERTION: si falla, el test se detiene inmediatamente.
     * Similar a "Stop on Error" en TestComplete.
     */
    @Test(priority = 1, description = "Login exitoso con admin/serenity")
    public void testLoginExitoso() {
        TestLogger.testStart("testLoginExitoso");

        // Acción
        loginPage.login(validUsername, validPassword);

        // Verificación (Hard assertion - detiene si falla)
        Assert.assertTrue(
                dashboardPage.isDashboardVisible(),
                "El dashboard debería estar visible después del login"
        );

        TestLogger.testPass("testLoginExitoso");
    }

    /**
     * Test 2: Login con password incorrecta
     */
    @Test(priority = 2, description = "Login falla con password incorrecta")
    public void testLoginPasswordIncorrecta() {
        TestLogger.testStart("testLoginPasswordIncorrecta");

        // Acción
        loginPage.login(validUsername, "password_incorrecta");

        // Verificación
        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Debería mostrarse un mensaje de error"
        );

        String errorMsg = loginPage.getErrorMessage();
        TestLogger.info("Mensaje de error: " + errorMsg);

        Assert.assertTrue(
                errorMsg.toLowerCase().contains("inválid") ||
                        errorMsg.toLowerCase().contains("invalid"),
                "El mensaje debería indicar credenciales inválidas"
        );

        TestLogger.testPass("testLoginPasswordIncorrecta");
    }

    /**
     * Test 3: Login con usuario incorrecto
     */
    @Test(priority = 3, description = "Login falla con usuario incorrecto")
    public void testLoginUsuarioIncorrecto() {
        TestLogger.testStart("testLoginUsuarioIncorrecto");

        // Acción
        loginPage.login("usuario_inexistente", validPassword);

        // Verificación
        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Debería mostrarse un mensaje de error"
        );

        TestLogger.testPass("testLoginUsuarioIncorrecto");
    }

    /**
     * Test 4: Login con campos vacíos
     */
    @Test(priority = 4, description = "Login falla con campos vacíos")
    public void testLoginCamposVacios() {
        TestLogger.testStart("testLoginCamposVacios");

        // Acción: click en login sin llenar nada
        loginPage.clickLogin();

        // El formulario no debería enviar o debería mostrar error
        // Verificamos que seguimos en la página de login
        Assert.assertTrue(
                loginPage.isUsernameFieldDisplayed(),
                "Deberíamos seguir en la página de login"
        );

        TestLogger.testPass("testLoginCamposVacios");
    }

    /**
     * Test 5: Ejemplo de SOFT ASSERTIONS
     *
     * Similar a "Stop on Warning" en TestComplete.
     * El test continúa aunque haya fallos, y reporta TODOS al final.
     */
    @Test(priority = 5, description = "Validaciones múltiples con Soft Assertions")
    public void testValidacionesMultiplesSoftAssert() {
        TestLogger.testStart("testValidacionesMultiplesSoftAssert");

        SoftAssert soft = new SoftAssert();

        // Verificación 1: Campo username visible
        soft.assertTrue(
                loginPage.isUsernameFieldDisplayed(),
                "Campo username debería estar visible"
        );

        // Verificación 2: Título de la página
        String pageTitle = DriverManager.getDriver().getTitle();
        TestLogger.info("Título de página: " + pageTitle);
        soft.assertTrue(
                pageTitle.contains("Serenity") || pageTitle.contains("Login"),
                "El título debería contener 'Serenity' o 'Login'"
        );

        // Verificación 3: URL correcta
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        TestLogger.info("URL actual: " + currentUrl);
        soft.assertTrue(
                currentUrl.contains("demo.serenity.is"),
                "La URL debería contener 'demo.serenity.is'"
        );

        // Al final: reporta TODOS los fallos juntos
        soft.assertAll();

        TestLogger.testPass("testValidacionesMultiplesSoftAssert");
    }

    /**
     * Test 6: Login y Logout completo
     */
    @Test(priority = 6, description = "Flujo completo: login, verificar dashboard, logout")
    public void testLoginLogoutCompleto() {
        TestLogger.testStart("testLoginLogoutCompleto");

        // Login
        loginPage.login(validUsername, validPassword);
        Assert.assertTrue(dashboardPage.isDashboardVisible(), "Dashboard visible");

        // Logout
        dashboardPage.logout();

        // Verificar que volvimos al login
        Assert.assertTrue(
                loginPage.isUsernameFieldDisplayed(),
                "Deberíamos ver el campo de username (página de login)"
        );

        TestLogger.testPass("testLoginLogoutCompleto");
    }
}