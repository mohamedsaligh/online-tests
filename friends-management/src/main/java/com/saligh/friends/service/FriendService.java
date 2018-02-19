package com.saligh.friends.service;

import com.saligh.friends.api.IFriendService;
import com.saligh.friends.bo.CreateRequest;
import com.saligh.friends.bo.Response;
import com.saligh.friends.dao.FriendDAO;
import com.saligh.friends.utils.AppUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by saligh on 19/2/18.
 */
@Slf4j
@Getter
@Setter
@Path("/friend")
public class FriendService implements IFriendService {


    private FriendDAO friendDAO;

    @Override
    public Response createFriend(CreateRequest createRequest) {

        if (!AppUtils.validateCreateFriends(createRequest)) {
            //Invalid request
            log.error(">> Invalid request");
        } else if (!validateEmails(createRequest.getFriends())) {
            //Invalid emails
            log.error(">> Invalid emails");
        }

        //saving in DB
        friendDAO.createFriend(createRequest);

        return new Response();
    }




    private boolean validateEmails(List<String> emails) {
        boolean isvalid = true;

        for (String email: emails) {
            isvalid = isvalid && AppUtils.isValidEmailAddress(email);
        }

        return isvalid;
    }

}
