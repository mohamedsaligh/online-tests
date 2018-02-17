package com.saligh.friends.utils;

import com.google.gson.GsonBuilder;

/**
 * Created by saligh on 18/2/18.
 */
public class AppUtils {

    public static String convertasJson(Object obj) {
        return (obj == null) ? null : new GsonBuilder().create().toJson(obj);
    }

}
