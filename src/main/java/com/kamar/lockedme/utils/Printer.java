package com.kamar.lockedme.utils;

import com.kamar.lockedme.enums.ConsoleColors;

public class Printer {
    public static void print(ConsoleColors color, String text){
        System.out.print(color);
        System.out.print(text);
        System.out.println(ConsoleColors.RESET);
    }
}
