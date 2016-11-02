package com.example.zolotuhinartem.simpleweather.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by zolotuhinartem on 01.11.16.
 */

public class SetToListConvertor {
    public static <T> List<T> setToList(Set<T> set) {
        int size = 0;
        if (set != null){
            size = set.size();
        }
        List<T> list;
        if (size != 0) {
            list =new ArrayList<>(size);
            for(T i: set) {
                list.add(i);
            }
        } else {
            list = new ArrayList<>(1);
        }

        return list;
    }
}
