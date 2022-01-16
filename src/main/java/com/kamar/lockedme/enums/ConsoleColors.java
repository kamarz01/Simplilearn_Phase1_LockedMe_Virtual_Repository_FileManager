package com.kamar.lockedme.enums;

public enum ConsoleColors {
    RESET("\033[0m"),
    RED("\033[0;31m"),
    GREEN("\033[0;32m"),
    YELLOW("\033[0;33m"),
    YELLOW_BRIGHT("\033[0;93m");    // YELLOW

    private final String code;

    ConsoleColors(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
