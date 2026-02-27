package com.cesar.qa.pages;

import com.cesar.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.qameta.allure.Step;

public class ClientesPage extends BasePage {

    // ===== LOCATORS =====

    private final By grillaCanvas = By.cssSelector("div.sg-body.sg-main.grid-canvas");
    private final By filas = By.cssSelector("div.slick-row");
    private final By viewport = By.cssSelector("div.sg-body.sg-main.slick-viewport");

    // ===== CONSTANTES DE COLUMNAS =====

    public static final int COL_ID = 0;
    public static final int COL_EMPRESA = 1;
    public static final int COL_CONTACTO = 2;
    public static final int COL_TITULO = 3;
    public static final int COL_REGION = 4;
    public static final int COL_CODIGO_POSTAL = 5;
    public static final int COL_PAIS = 6;
    public static final int COL_CIUDAD = 7;
    public static final int COL_TELEFONO = 8;
    public static final int COL_FAX = 9;
    public static final int COL_REPRESENTANTES = 10;

    public static final int TOTAL_COLUMNAS = 11;

    // Datos leídos de la grilla: Map<ID, String[]>
    private Map<String, String[]> datosGrilla;

    // ===== MÉTODOS =====

    public void esperarGrillaCargada() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(grillaCanvas));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(filas));
    }

    /**
     * Lee TODA la grilla una sola vez scrolleando de arriba a abajo.
     * Guarda los datos en memoria para consultas rápidas.
     */
    @Step("Leer grilla completa de clientes")
    public void leerGrillaCompleta() {
        datosGrilla = new HashMap<>();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement vp = driver.findElement(viewport);

        int alturaTotal = ((Number) js.executeScript(
                "return arguments[0].scrollHeight", vp)).intValue();
        int paso = ((Number) js.executeScript(
                "return arguments[0].clientHeight", vp)).intValue();

        js.executeScript("arguments[0].scrollTop = 0", vp);
        pausa(300);

        for (int pos = 0; pos <= alturaTotal; pos += paso / 2) {
            js.executeScript("arguments[0].scrollTop = arguments[1]", vp, pos);
            pausa(200);

            List<WebElement> filasVisibles = driver.findElements(filas);
            for (WebElement fila : filasVisibles) {
                WebElement celdaId = fila.findElement(By.cssSelector("div.slick-cell.l0"));
                String id = celdaId.getText().trim();

                if (!id.isEmpty() && !datosGrilla.containsKey(id)) {
                    String[] valores = new String[TOTAL_COLUMNAS];
                    for (int col = 0; col < TOTAL_COLUMNAS; col++) {
                        try {
                            WebElement celda = fila.findElement(
                                    By.cssSelector("div.slick-cell.l" + col));
                            valores[col] = celda.getText().trim();
                        } catch (Exception e) {
                            valores[col] = "";
                        }
                    }
                    datosGrilla.put(id, valores);
                }
            }
        }

        // Volver al inicio
        js.executeScript("arguments[0].scrollTop = 0", vp);
    }

    /**
     * Obtiene un valor de la grilla ya leída en memoria.
     * No toca el DOM — es instantáneo.
     */
    @Step("Obtener valor de cliente {clienteId} en columna {indiceColumna}")
    public String obtenerValorPorId(String clienteId, int indiceColumna) {
        if (datosGrilla == null) {
            throw new RuntimeException("Llamá a leerGrillaCompleta() antes de consultar datos");
        }

        String[] fila = datosGrilla.get(clienteId);
        if (fila == null) {
            throw new RuntimeException("Cliente con ID '" + clienteId + "' no encontrado en la grilla");
        }

        return fila[indiceColumna];
    }

    public int contarRegistrosLeidos() {
        return datosGrilla != null ? datosGrilla.size() : 0;
    }

    private void pausa(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}