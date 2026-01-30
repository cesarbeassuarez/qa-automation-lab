package com.cesar.qa.utils;

import org.checkerframework.dataflow.qual.AssertMethod;

public class check {
    // 1) Comparar strings (expected vs actual)
    public static void equals(String actual, String expected, String context) {
        if (actual == null) actual = "null";
        if (expected == null) expected = "null";

        if (!actual.equals(expected)) {
            System.out.println(
                    "❌ fallo. " + context +
                    "\n   Expected: [" + expected + "]" +
                    "\n   Actual:   [" + actual + "]");
        } else {
            System.out.println("✅ " + context + " -> OK");
        }
    }

    // 2) Validar visibilidad (boolean)
    public static void visible(boolean isVisible, String context) {
        if (!isVisible) {
            System.out.println("❌ fallo. " + context + " -> NO visible");
        } else {
            System.out.println("✅ " + context + " -> visible");
        }
    }
    // 3) Valida
    public static void isFalse(boolean condition, String context) {
        if (condition) {
            System.out.println("❌ fallo. " + context + " (se esperaba FALSE y fue TRUE)");
        } else {
            System.out.println("✅ " + context + " -> OK");
        }
    }
}
