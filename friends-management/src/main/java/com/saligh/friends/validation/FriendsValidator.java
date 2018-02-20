package com.saligh.friends.validation;

import com.saligh.friends.bo.*;
import com.saligh.friends.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 20/02.
 */
@Slf4j
public class FriendsValidator {

    public static CreateResponse validateCreateRequest(CreateRequest request) {
        if (request == null || request.getFriends() == null) {
            return new CreateResponse(false, Messages.createRequestInvalid);
        } else if (request.getFriends().isEmpty() || request.getFriends().size() != 2) {
            return new CreateResponse(false, Messages.createRequest2EmailAllowed);
        } else if (!validateEmails(request.getFriends())) {
            return new CreateResponse(false, Messages.createRequestInvalidEmailIds);
        } else if (areBothEmailsSame(request.getFriends().get(0), request.getFriends().get(1))) {
            return new CreateResponse(false, Messages.createRequestEmailIdsAreSame);
        }

        return new CreateResponse(true, Messages.createFriendsSuccessful);
    }

    public static UserResponse validateUserRequest(UserRequest request) {
        if (request == null || request.getEmail() == null) {
            return new UserResponse(false, null, 0 , Messages.createRequestInvalid);
        } else if (!AppUtils.isValidEmailAddress(request.getEmail())) {
            return new UserResponse(false, null, 0 , Messages.createRequestInvalidEmailIds);
        }

        return new UserResponse(true, null, 0 , null);
    }

    public static UserResponse validateCommonRequest(CreateRequest request) {
        if (request == null || request.getFriends() == null) {
            return new UserResponse(false, null, 0 , Messages.createRequestInvalid);
        } else if (request.getFriends().isEmpty() || request.getFriends().size() != 2) {
            return new UserResponse(false, null, 0 , Messages.createRequest2EmailAllowed);
        } else if (!validateEmails(request.getFriends())) {
            return new UserResponse(false, null, 0 , Messages.createRequestInvalidEmailIds);
        } else if (areBothEmailsSame(request.getFriends().get(0), request.getFriends().get(1))) {
            return new UserResponse(false, null, 0 , Messages.createRequestEmailIdsAreSame);
        }

        return new UserResponse(true, null, 0 , null);
    }

    public static CreateResponse validateSubscribeRequest(SubscribeRequest request) {
        if (request == null || request.getRequestor() == null || request.getTarget() == null) {
            return new CreateResponse(false, Messages.createRequestInvalid);
        } else if (!AppUtils.isValidEmailAddress(request.getRequestor()) || !AppUtils.isValidEmailAddress(request.getTarget())) {
            return new CreateResponse(false, Messages.createRequestInvalidEmailIds);
        } else if (areBothEmailsSame(request.getRequestor(), request.getTarget())) {
            return new CreateResponse(false, Messages.createRequestEmailIdsAreSame);
        }

        return new CreateResponse(true, Messages.createFriendsSuccessful);
    }

    public static UpdateResponse validateUpdateEmailList(UpdateRequest request) {
        if (request == null || request.getSender() == null || request.getText() == null) {
            return new UpdateResponse(false, null, Messages.createRequestInvalid);
        } else if (!AppUtils.isValidEmailAddress(request.getSender())) {
            return new UpdateResponse(false, null, Messages.createRequestInvalidEmailIds);
        }

        return new UpdateResponse(true, new ArrayList<>(), Messages.createFriendsSuccessful);
    }

    private static boolean areBothEmailsSame(String email1, String email2) {
        return email1.equalsIgnoreCase(email2);
    }

    private static boolean validateEmails(List<String> emails) {
        boolean isvalid = true;

        for (String email: emails) {
            isvalid = isvalid && AppUtils.isValidEmailAddress(email);
        }

        return isvalid;
    }

}
