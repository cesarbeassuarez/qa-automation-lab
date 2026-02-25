package com.cesar.qa.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static Object[][] leerDatos(String rutaArchivo, String nombreHoja) {
        List<Object[]> datos = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(rutaArchivo);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(nombreHoja);
            if (sheet == null) {
                throw new RuntimeException("Hoja '" + nombreHoja + "' no encontrada en " + rutaArchivo);
            }

            int totalFilas = sheet.getPhysicalNumberOfRows();
            int totalColumnas = sheet.getRow(0).getPhysicalNumberOfCells();

            // Empieza en 1 para saltear el header
            for (int i = 1; i < totalFilas; i++) {
                Row fila = sheet.getRow(i);
                if (fila == null) continue;

                Object[] valores = new Object[totalColumnas];
                for (int j = 0; j < totalColumnas; j++) {
                    Cell celda = fila.getCell(j);
                    valores[j] = obtenerValorCelda(celda);
                }
                datos.add(valores);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error leyendo Excel: " + rutaArchivo, e);
        }

        return datos.toArray(new Object[0][]);
    }

    private static String obtenerValorCelda(Cell celda) {
        if (celda == null) return "";

        return switch (celda.getCellType()) {
            case STRING -> celda.getStringCellValue().trim();
            case NUMERIC -> {
                double valor = celda.getNumericCellValue();
                if (valor == Math.floor(valor)) {
                    yield String.valueOf((int) valor);
                }
                yield String.valueOf(valor);
            }
            case BOOLEAN -> String.valueOf(celda.getBooleanCellValue());
            case BLANK -> "";
            default -> "";
        };
    }
}