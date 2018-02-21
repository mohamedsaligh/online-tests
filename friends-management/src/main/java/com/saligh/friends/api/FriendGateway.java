package com.saligh.friends.api;

import com.saligh.friends.bo.*;
import com.saligh.friends.service.FriendService;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by saligh on 19/2/18.
 */
@Getter
@Setter
@Path("/friend")
public class FriendGateway {

    private FriendService friendService;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public CreateResponse createFriend(CreateRequest createRequest) {
        return friendService.createFriend(createRequest);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/friends")
    public UserResponse getFriendsList(UserRequest userRequest) {
        return friendService.getFriendsList(userRequest);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/mutual")
    public UserResponse getCommonFriends(CreateRequest createRequest) {
        return friendService.getCommonFriends(createRequest);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/subscribe")
    public CreateResponse subscribeUsers(SubscribeRequest subscribeRequest) {
        return friendService.subscribeUsers(subscribeRequest);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/block")
    public CreateResponse blockUsers(SubscribeRequest subscribeRequest) {
        return friendService.blockUsers(subscribeRequest);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updates")
    public UpdateResponse emailstoGetUpdates(UpdateRequest updateRequest) {
        return friendService.emailstoGetUpdates(updateRequest);
    }

}
