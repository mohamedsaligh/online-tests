package com.saligh.friends.api;

import com.saligh.friends.bo.AddFriend;
import com.saligh.friends.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saligh on 17/2/18.
 */
@Slf4j
@Path("/friends")
public class FriendsService {

    @PUT
    @Path("/test/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AddFriend testService(AddFriend friend) {
        log.info("Test Service: " + AppUtils.convertasJson(friend));

        AddFriend friends = new AddFriend();
        List<String> list = new ArrayList<String>();
        list.add("Mohamed");
        list.add(friend.getFriends().get(1));
        friends.setFriends(list);

        return friends;
    }


}
