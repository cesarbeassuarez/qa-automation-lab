package com.cesar.qa.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestLogger {

    // ══════════════════════════════════════════════════════════════
    // Logger de SLF4J: el que realmente escribe los mensajes
    // ══════════════════════════════════════════════════════════════
    private static final Logger logger = LoggerFactory.getLogger(TestLogger.class);

    // Formato de hora: 10:15:23.456
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    // ══════════════════════════════════════════════════════════════
    // NIVELES BÁSICOS: info, warn, error
    // ══════════════════════════════════════════════════════════════

    /**
     * Mensaje informativo normal
     * Ejemplo: "Navegando a https://..."
     */
    public static void info(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.info("[{}] {}", timestamp, message);
    }

    /**
     * Advertencia: algo raro pero no fatal
     * Ejemplo: "Elemento no visible, usando JavaScript click"
     */
    public static void warn(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.warn("[{}] ⚠️ {}", timestamp, message);
    }

    /**
     * Error: algo falló
     * Ejemplo: "No se encontró el elemento #loginBtn"
     */
    public static void error(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.error("[{}] ❌ {}", timestamp, message);
    }

    // ══════════════════════════════════════════════════════════════
    // PASOS DE TEST: para marcar acciones importantes
    // ══════════════════════════════════════════════════════════════

    /**
     * Marca un paso del test
     * Ejemplo: "Ingresando credenciales"
     */
    public static void step(String stepDescription) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.info("[{}] ▶ STEP: {}", timestamp, stepDescription);
    }

    // ══════════════════════════════════════════════════════════════
    // INICIO/FIN DE TEST: para ver claramente dónde empieza y termina
    // ══════════════════════════════════════════════════════════════

    /**
     * Marca el inicio de un test
     */
    public static void testStart(String testName) {
        logger.info("═══════════════════════════════════════════════════════");
        logger.info("▶ TEST START: {}", testName);
        logger.info("═══════════════════════════════════════════════════════");
    }

    /**
     * Marca el fin exitoso de un test
     */
    public static void testPass(String testName) {
        logger.info("✅ TEST PASSED: {}", testName);
        logger.info("───────────────────────────────────────────────────────");
    }

    /**
     * Marca el fin fallido de un test
     */
    public static void testFail(String testName, String reason) {
        logger.error("❌ TEST FAILED: {} - Reason: {}", testName, reason);
        logger.info("───────────────────────────────────────────────────────");
    }
}