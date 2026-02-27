package com.cesar.qa.data;

import org.testng.annotations.DataProvider;

public class TestData {

    // ===== LOGIN =====

    @DataProvider(name = "credencialesInvalidas")
    public static Object[][] credencialesInvalidas() {
        return new Object[][] {
                {"Password incorrecta","admin", "mal", "Error de validación: ¡Nombres de usuario o contraseña inválidos!"},
                {"Usuario incorrecto","mal", "serenity", "Error de validación: ¡Nombres de usuario o contraseña inválidos!"},
                {"Espacios en blanco"," ", " ", "Por favor, valide los campos vacíoss o inválidos (marcados en rojo) antes de enviar el formulario"}
        };
    }

    // ===== CLIENTES =====
    // (futuro: DataProviders livianos de clientes acá)

    // ===== PEDIDOS =====
    // (futuro: DataProviders livianos de pedidos acá)
}