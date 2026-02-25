package com.cesar.qa.data;

import com.cesar.qa.utils.ExcelReader;
import org.testng.annotations.DataProvider;

public class ClientesTestData {

    private static final String RUTA_EXCEL = "src/test/resources/testdata/clientes-data.xlsx";

    @DataProvider(name = "datosClientes")
    public static Object[][] datosClientes() {
        return ExcelReader.leerDatos(RUTA_EXCEL, "Clientes");
    }
}