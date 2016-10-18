package com.example.zolotuhinartem.contactlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zolotuhinartem on 18.10.16.
 */

public class ContactListGenerator {
    public static List<Contact> generateNumbersList(int size) {
        ArrayList<Contact> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(new Contact(generateNumber(11)));
        }
        return list;
    }

    public static String generateNumber(int size) {
        StringBuilder result = new StringBuilder(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            result.append(Integer.toString(Math.abs(random.nextInt() % 10)));
        }
        return result.toString();
    }
}
