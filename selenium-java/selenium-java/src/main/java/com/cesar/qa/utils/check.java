package com.cesar.qa.utils;

public class check {
    // 1) Comparar strings (expected vs actual)
    public static void equals(String actual, String expected, String context) {
        if (actual == null) actual = "null";
        if (expected == null) expected = "null";

        if (!actual.equals(expected)) {
            throw new AssertionError(
                    "❌ " + context +
                            "\n   Expected: [" + expected + "]" +
                            "\n   Actual:   [" + actual + "]"
            );
        }

        System.out.println("✅ " + context + " -> OK");
    }

    // 2) Validar visibilidad (boolean)
    public static void visible(boolean isVisible, String context) {
        if (!isVisible) {
            throw new AssertionError("❌ " + context + " -> NO visible");
        }
        System.out.println("✅ " + context + " -> visible");
    }
}
