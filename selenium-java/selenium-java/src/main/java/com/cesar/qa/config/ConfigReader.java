// Leer datos desde un archivo .properties
// Si necesitas manejar configuraciones externas sin tocar el código, usa archivos .properties.
/* e.g:
Ejemplo de un archivo config.properties:

base.url=https://demo.serenity.is/
login.username=admin
login.password=serenity

Cómo usarlo en un test:
String baseUrl = ConfigReader.getProperty("base.url");
System.out.println("URL Base: " + baseUrl);

Ventaja: Puedes cambiar credenciales o URLs sin modificar el código.
*/

package com.cesar.qa.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}


