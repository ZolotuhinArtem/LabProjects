package com.example.zolotuhinartem.contactlist;

/**
 * Created by zolotuhinartem on 18.10.16.
 */

public class ValidDataChecker {
    public static boolean checkNumber(String number) {
        boolean f;
        f = true;
        if (number != null) {
            if (number.length() > 0) {
                for (int i = 0; i < number.length(); i++) {
                    if (((int) number.charAt(i) > (int) '9') || ((int) number.charAt(i) < (int) '0')) {
                        f = false;
                        break;
                    }
                }
            } else {
                f = false;
            }
        } else {
            f = false;
        }
        return f;
    }
}
