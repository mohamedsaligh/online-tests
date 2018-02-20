package com.saligh.friends.validation;

import com.saligh.friends.bo.CreateRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by mohamed on 20/02.
 */
@Slf4j
public class FriendsValidator {

    public static boolean validateCreateRequest(CreateRequest request) {
        if (request != null && request.getFriends() != null) {
            if (!request.getFriends().isEmpty() && request.getFriends().size() == 2) {
                return true;
            }
        }

        return false;
    }

}
