package com.saligh.friends.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.saligh.friends.bo.CreateRequest;
import com.saligh.friends.bo.Response;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;

/**
 * Created by saligh on 19/2/18.
 */
public interface IFriendService {

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response createFriend(CreateRequest createRequest);

}
