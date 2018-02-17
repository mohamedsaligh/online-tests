package com.saligh.friends.api;

import com.saligh.friends.bo.AddFriend;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saligh on 17/2/18.
 */
@Slf4j
@Path("/friends")
public class FriendsService {

    @GET
    @Path("/test/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public AddFriend testService(@PathParam("name") String name) {
        log.info("Test Service: " + name);

        AddFriend friends = new AddFriend();
        List<String> list = new ArrayList<String>();
        list.add("Mohamed");
        list.add(name);
        friends.setFriends(list);

        return friends;
    }


}
