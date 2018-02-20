package com.saligh.friends.utils;

import com.saligh.friends.bo.CreateRequest;
import com.saligh.friends.db.Friends;
import com.saligh.friends.db.FriendsPK;
import com.saligh.friends.db.Users;

import java.util.UUID;

/**
 * Created by saligh on 20/2/18.
 */
public final class ObjectConverter {

    public static Users createUsers(String username) {
        Users users = new Users();

        users.setUsername(username);
        users.setPassword(null);
        users.setFriendCount(1);

        return users;
    }

    public static Friends createFriends(String email1, String email2) {
        FriendsPK friendsPK = new FriendsPK();
        friendsPK.setFriendOne(email1);
        friendsPK.setFriendTwo(email2);

        Friends friends = new Friends();
        friends.setFriendsPK(friendsPK);
        friends.setStatus(Status.FRIEND.getStatus());

        return friends;
    }

    public static Friends createSubscription(String requestor, String target) {
        FriendsPK friendsPK = new FriendsPK();
        friendsPK.setFriendOne(requestor);
        friendsPK.setFriendTwo(target);

        Friends friends = new Friends();
        friends.setFriendsPK(friendsPK);
        friends.setStatus(Status.SUBSCRIBED.getStatus());

        return friends;
    }

    public static Friends createBlocking(String requestor, String target) {
        FriendsPK friendsPK = new FriendsPK();
        friendsPK.setFriendOne(requestor);
        friendsPK.setFriendTwo(target);

        Friends friends = new Friends();
        friends.setFriendsPK(friendsPK);
        friends.setStatus(Status.BLOCKED.getStatus());

        return friends;
    }
}
