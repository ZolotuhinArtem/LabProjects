package com.example.zolotuhinartem.simpleweather.utils;

/**
 * Created by zolotuhinartem on 02.11.16.
 */

public class StringUtils {

    public static String deleteSpacesOnStartAndEnd(String line) {
        StringBuilder str = new StringBuilder(line);

        for (int i = 0; i < str.length(); i++) {
            if ((str.charAt(i) == ' ') || (str.charAt(i) == '\t')) {
                str.deleteCharAt(i);
                i--;
            } else {
                break;
            }
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            if ((str.charAt(i) == ' ') || (str.charAt(i) == '\t')) {
                str.deleteCharAt(i);
            } else {
                break;
            }
        }

        return str.toString();

    }
    public static String deleteSpaces(String line) {
        StringBuilder str = new StringBuilder(line);

        for (int i = 0; i < str.length(); i++) {
            if ((str.charAt(i) == ' ') || (str.charAt(i) == '\t')) {
                str.deleteCharAt(i);
                i--;
            }
        }
        return str.toString();

    }

}
