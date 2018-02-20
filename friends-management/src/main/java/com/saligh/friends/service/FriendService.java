package com.saligh.friends.service;

import com.saligh.friends.bo.*;
import com.saligh.friends.dao.FriendDAO;
import com.saligh.friends.db.Friends;
import com.saligh.friends.db.Users;
import com.saligh.friends.utils.ObjectConverter;
import com.saligh.friends.utils.Status;
import com.saligh.friends.validation.FriendsValidator;
import com.saligh.friends.validation.Messages;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by saligh on 19/2/18.
 */
@Slf4j
@Getter
@Setter
public class FriendService {

    private FriendDAO friendDAO;

    @Transactional
    public CreateResponse createFriend(CreateRequest createRequest) {
        CreateResponse createResponse = FriendsValidator.validateCreateRequest(createRequest);

        if (!createResponse.isSuccess()) {
            log.error("createFriend: Validation Failed - {}", createResponse.getMessage());
            return createResponse;
        }

        List<Users> users = new ArrayList<>();

        log.info("createFriend: Validation Passed. Proceeding to save into DB...");
        createUsersProfile(createRequest, users);
        createFriendsConnection(createRequest.getFriends(), createResponse);
        updateFriendsCount(users);
        return createResponse;
    }

    private void createUsersProfile(CreateRequest createRequest, List<Users> users) {
        for(String email: createRequest.getFriends()) {
            Users user = friendDAO.getUsers(email);
            if(user != null) {
                user.setNew(false);
                users.add(user);
                log.info("createUsersProfile: user ({}) is already exists.", email);
            } else {
                user = ObjectConverter.createUsers(email);
                user.setNew(true);
                friendDAO.createUsers(user);
                users.add(user);
                log.info("createUsersProfile: user ({}) creation is successful.", users);
            }
        }
    }

    private void createFriendsConnection(List<String> emails, CreateResponse createResponse) {
        List<Friends> friends = new ArrayList<Friends>();
        log.info("createFriendsConnection: checking if both users are friends.");
        if (friendDAO.checkIfFriends(emails.get(0), emails.get(1))) {
            friends.add(ObjectConverter.createFriends(emails.get(0), emails.get(1)));
            friends.add(ObjectConverter.createFriends(emails.get(1), emails.get(0)));
        } else {
            createResponse.setSuccess(false);
            createResponse.setMessage(Messages.createFriendsAlreadyHasConnection);
            log.info("createFriendsConnection: already they are having friend connection.");
        }

        friendDAO.saveFriends(friends, Status.FRIEND.getStatus());
    }

    private void updateFriendsCount(List<Users> users) {
        for(Users user: users) {
            if (!user.isNew()) {
                user.setFriendCount(user.getFriendCount() + 1);
                friendDAO.updateUsers(user);
            }
            log.info("updateFriendsCount: friends count for {} is {}", user.getUsername(), user.getFriendCount());
        }
    }


    @Transactional
    public UserResponse getFriendsList(UserRequest userRequest) {
        UserResponse userResponse = FriendsValidator.validateUserRequest(userRequest);

        if (!userResponse.isSuccess()) {
            log.error("getFriendsList: Validation Failed - {}", userResponse.getMessage());
            return userResponse;
        }

        Users users = friendDAO.getUsers(userRequest.getEmail());
        if (users == null) {
            userResponse.setSuccess(false);
            userResponse.setMessage(Messages.friendslistUserNotAvailable);
            log.error("getFriendsList: User is not available for provided email - {}", userRequest.getEmail());
            return userResponse;
        }

        userResponse.setCount(users.getFriendCount());
        userResponse.setFriends(friendDAO.getFriendsList(userRequest.getEmail()));
        userResponse.setMessage(Messages.friendslistUserSuccessful);
        log.info("getFriendsList: friends list for {} - ", userRequest.getEmail(), userResponse);

        return userResponse;
    }

    @Transactional
    public UserResponse getCommonFriends(CreateRequest createRequest) {
        UserResponse userResponse = FriendsValidator.validateCommonRequest(createRequest);

        if (!userResponse.isSuccess()) {
            log.error("getCommonFriends: Validation Failed - {}", userResponse.getMessage());
            return userResponse;
        }

        List<Users> users = new ArrayList<>();
        Users user1 = friendDAO.getUsers(createRequest.getFriends().get(0));
        if (user1 != null) { users.add(user1); }
        Users user2 = friendDAO.getUsers(createRequest.getFriends().get(1));
        if (user2 != null) { users.add(user2); }
        if (users.size() < 2) {
            userResponse.setSuccess(false);
            userResponse.setMessage(Messages.commonFriendsUserNotAvailable);
            log.error("getCommonFriends: users not available - {}", userResponse.getMessage());
            return userResponse;
        }

        List<String> friends = friendDAO.getCommonFriends(createRequest.getFriends().get(0), createRequest.getFriends().get(1));
        userResponse.setFriends(friends);
        userResponse.setCount(friends.size());
        userResponse.setMessage((friends.size() > 0) ? Messages.friendslistUserSuccessful : Messages.commonFriendsEmptyList);
        log.info("getCommonFriends: common friends count {} - ", friends.size());

        return userResponse;
    }


    @Transactional
    public CreateResponse subscribeUsers(SubscribeRequest subscribeRequest) {
        CreateResponse createResponse = FriendsValidator.validateSubscribeRequest(subscribeRequest);

        if (!createResponse.isSuccess()) {
            log.error("subscribeUsers: Validation Failed - {}", createResponse.getMessage());
            return createResponse;
        }

        List<Users> users = new ArrayList<>();
        Users user1 = friendDAO.getUsers(subscribeRequest.getRequestor());
        if (user1 != null) { users.add(user1); }
        Users user2 = friendDAO.getUsers(subscribeRequest.getTarget());
        if (user2 != null) { users.add(user2); }
        if (users.size() < 2) {
            createResponse.setSuccess(false);
            createResponse.setMessage(Messages.subscribeUsersRequestorTargetNotValidUser);
            log.error("subscribeUsers: users not available - {}", createResponse.getMessage());
            return createResponse;
        }

        if(friendDAO.checkIfAlreadyFriends(subscribeRequest.getRequestor(), subscribeRequest.getTarget())) {
            createResponse.setSuccess(false);
            createResponse.setMessage(Messages.subscribeUsersAlreadyFriends);
            log.error("subscribeUsers: already friends - {}", createResponse.getMessage());
            return createResponse;
        }

        if (friendDAO.checkIfAlreadySubscribed(subscribeRequest.getRequestor(), subscribeRequest.getTarget())) {
            createResponse.setSuccess(false);
            createResponse.setMessage(Messages.subscribeUsersAlreadySubscribed);
            log.error("subscribeUsers: already friends - {}", createResponse.getMessage());
            return createResponse;
        }

        Friends subscribe = ObjectConverter.createSubscription(subscribeRequest.getRequestor(), subscribeRequest.getTarget());
        friendDAO.createFriends(subscribe);
        createResponse.setMessage(Messages.subscribeUsersSuccessful);
        log.info("subscribeUsers: {} is successfully subscribed to {} - ", subscribeRequest.getRequestor(), subscribeRequest.getTarget());

        return createResponse;
    }


    @Transactional
    public CreateResponse blockUsers(SubscribeRequest subscribeRequest) {
        CreateResponse createResponse = FriendsValidator.validateSubscribeRequest(subscribeRequest);

        if (!createResponse.isSuccess()) {
            log.error("blockUsers: Validation Failed - {}", createResponse.getMessage());
            return createResponse;
        }

        Users requestor = friendDAO.getUsers(subscribeRequest.getRequestor());
        Users target = friendDAO.getUsers(subscribeRequest.getTarget());
        if (requestor == null || target == null) {
            createResponse.setSuccess(false);
            createResponse.setMessage(Messages.subscribeUsersRequestorTargetNotValidUser);
            log.error("blockUsers: users not available - {}", createResponse.getMessage());
            return createResponse;
        }

        Friends block = friendDAO.selectForBlocking(subscribeRequest.getRequestor(), subscribeRequest.getTarget());
        if (block != null) {
            if (Status.BLOCKED.getStatus().equals(block.getStatus())) {
                createResponse.setSuccess(false);
                createResponse.setMessage(Messages.blockingUserIsAlreadyBlocked);
                log.info("blockUsers: Requestor already blocked the target.");

                return createResponse;
            }

            log.info("blockUsers: requestor blocking target.");
            block.setStatus(Status.BLOCKED.getStatus());
            friendDAO.updateFriends(block);

            createResponse.setMessage(Messages.blockingUserIsSuccessful);
            return createResponse;
        }

        log.info("blockUsers: requestor blocking target.");
        Friends subscribe = ObjectConverter.createBlocking(subscribeRequest.getRequestor(), subscribeRequest.getTarget());
        friendDAO.createFriends(subscribe);
        createResponse.setMessage(Messages.blockingUserIsSuccessful);

        log.info("blockUsers: requestor successfully blocked the target.");
        return createResponse;
    }


    @Transactional
    public UpdateResponse emailstoGetUpdates(UpdateRequest updateRequest) {
        UpdateResponse updateResponse = FriendsValidator.validateUpdateEmailList(updateRequest);

        if (!updateResponse.isSuccess()) {
            log.error("emailstoGetUpdates: Validation Failed - {}", updateResponse.getMessage());
            return updateResponse;
        }

        Users sender = friendDAO.getUsers(updateRequest.getSender());
        if (sender == null) {
            updateResponse.setSuccess(false);
            updateResponse.setMessage(Messages.subscribeUsersRequestorTargetNotValidUser);
            log.error("emailstoGetUpdates: users not available - {}", updateResponse.getMessage());
            return updateResponse;
        }

        Set<String> emailList = new HashSet<>();
        List<String> friends = friendDAO.getAllFriendsSubscribers(updateRequest.getSender());

        emailList.addAll(friends);

        List<String> allOthers = friendDAO.getAllUsersExceptBlocked(updateRequest.getSender());
        String patternString = "\\b(" + StringUtils.join(allOthers, "|") + ")\\b";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(updateRequest.getText());

        while(matcher.find()) {
            String id = matcher.group(1);
            if (!"".equalsIgnoreCase(id)) {
                emailList.add(id);
            }
        }

        updateResponse.getRecipients().addAll(emailList);
        updateResponse.setMessage(Messages.emailListOfUserIsSuccessful);
        log.info("emailstoGetUpdates: email list retrieval successful.");

        return updateResponse;
    }

}
