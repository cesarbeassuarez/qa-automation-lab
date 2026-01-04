package com.cesar.qa.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    // Properties: objeto que guarda las claves/valores del archivo
    private static Properties properties;

    // Nombre del archivo a leer
    private static final String CONFIG_FILE = "config.properties";

    // ══════════════════════════════════════════════════════════════
    // STATIC BLOCK: se ejecuta UNA sola vez cuando la clase se carga
    // ══════════════════════════════════════════════════════════════
    static {
        properties = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {

            // Si no encuentra el archivo, lanza error
            if (input == null) {
                throw new RuntimeException("No se encontró " + CONFIG_FILE);
            }

            // Carga el contenido del archivo en el objeto properties
            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Error al cargar " + CONFIG_FILE, e);
        }
    }

    // ══════════════════════════════════════════════════════════════
    // MÉTODOS GENÉRICOS: obtener valores por clave
    // ══════════════════════════════════════════════════════════════

    // Obtiene un valor como String
    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Propiedad no encontrada: " + key);
        }
        return value;
    }

    // Obtiene un valor como int (convierte el String a número)
    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    // Obtiene un valor como boolean (convierte "true"/"false")
    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    // ══════════════════════════════════════════════════════════════
    // MÉTODOS DE CONVENIENCIA: los más usados, con nombres claros
    // ══════════════════════════════════════════════════════════════

    public static String getBaseUrl() {
        return get("base.url");
    }

    public static String getUsername() {
        return get("login.username");
    }

    public static String getPassword() {
        return get("login.password");
    }

    public static int getExplicitTimeout() {
        return getInt("timeout.explicit");
    }

    public static int getActionDelay() {
        return getInt("action.delay");
    }

    public static boolean isHeadless() {
        return getBoolean("headless");
    }

    public static boolean shouldMaximize() {
        return getBoolean("maximize");
    }
}