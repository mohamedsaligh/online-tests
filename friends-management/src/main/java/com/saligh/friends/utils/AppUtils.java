package com.saligh.friends.utils;

import com.google.gson.GsonBuilder;
import com.saligh.friends.bo.CreateRequest;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by saligh on 18/2/18.
 */
public class AppUtils {

    public static String convertasJson(Object obj) {
        return (obj == null) ? null : new GsonBuilder().create().toJson(obj);
    }

    public static boolean validateCreateFriends(CreateRequest request) {
        if (request != null && request.getFriends() != null) {
            if (!request.getFriends().isEmpty() && request.getFriends().size() == 2) {
                return true;
            }
        }

        return false;
    }

    public static boolean isValidEmailAddress(String email) {
        try {
            new InternetAddress(email).validate();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

}
