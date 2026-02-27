package com.cesar.qa.tests.clientes;

import com.cesar.qa.config.ConfigReader;
import com.cesar.qa.config.DriverManager;
import com.cesar.qa.data.ClientesTestData;
import com.cesar.qa.pages.ClientesPage;
import com.cesar.qa.pages.DashboardPage;
import com.cesar.qa.pages.LoginPage;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class ClientesTests {

    private ClientesPage clientesPage;

    @BeforeClass
    public void setupYNavegar() {
        System.out.println(">>> ARRANCANDO setupYNavegar");
        DriverManager.initDriver();

        String baseUrl = ConfigReader.getProperty("base.url");
        DriverManager.getDriver().get(baseUrl);

        LoginPage loginPage = new LoginPage();
        loginPage.loginComo("admin", "serenity");

        DashboardPage dashboard = new DashboardPage();
        Assert.assertTrue(dashboard.estaVisible(), "Dashboard visible después de login");
        dashboard.irAClientes();

        clientesPage = new ClientesPage();
        clientesPage.esperarGrillaCargada();
        clientesPage.leerGrillaCompleta();  // ← Lee toda la grilla UNA vez
    }

    @Test(dataProvider = "datosClientes", dataProviderClass = ClientesTestData.class)
    @Description("Valida datos de cliente en grilla contra Excel")
    @Severity(SeverityLevel.NORMAL)
    public void validarDatosCliente(String id, String empresa, String contacto,
                                    String titulo, String region, String codigoPostal,
                                    String pais, String ciudad, String telefono,
                                    String fax, String representantes) {

        Assert.assertEquals(
                clientesPage.obtenerValorPorId(id, ClientesPage.COL_EMPRESA),
                empresa, id + " - Empresa");

        Assert.assertEquals(
                clientesPage.obtenerValorPorId(id, ClientesPage.COL_CONTACTO),
                contacto, id + " - Contacto");

        Assert.assertEquals(
                clientesPage.obtenerValorPorId(id, ClientesPage.COL_TITULO),
                titulo, id + " - Título");

        Assert.assertEquals(
                clientesPage.obtenerValorPorId(id, ClientesPage.COL_REGION),
                region, id + " - Región");

        Assert.assertEquals(
                clientesPage.obtenerValorPorId(id, ClientesPage.COL_CODIGO_POSTAL),
                codigoPostal, id + " - Código Postal");

        Assert.assertEquals(
                clientesPage.obtenerValorPorId(id, ClientesPage.COL_PAIS),
                pais, id + " - País");

        Assert.assertEquals(
                clientesPage.obtenerValorPorId(id, ClientesPage.COL_CIUDAD),
                ciudad, id + " - Ciudad");

        Assert.assertEquals(
                clientesPage.obtenerValorPorId(id, ClientesPage.COL_TELEFONO),
                telefono, id + " - Teléfono");

        Assert.assertEquals(
                clientesPage.obtenerValorPorId(id, ClientesPage.COL_FAX),
                fax, id + " - Fax");

        Assert.assertEquals(
                clientesPage.obtenerValorPorId(id, ClientesPage.COL_REPRESENTANTES),
                representantes, id + " - Representantes");
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}