package com.saligh.friends.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
 * Created by saligh on 20/2/18.
 */
@Component
@PropertySource("classpath:application.properties")
public class Messages {

    public static String createRequestInvalid;

    public static String createRequest2EmailAllowed;

    public static String createRequestInvalidEmailIds;

    public static String createRequestEmailIdsAreSame;

    public static String createFriendsAlreadyHasConnection;

    public static String createFriendsSuccessful;

    public static String friendslistUserNotAvailable;

    public static String friendslistUserSuccessful;

    public static String commonFriendsUserNotAvailable;

    public static String commonFriendsEmptyList;

    public static String subscribeUsersRequestorTargetNotValidUser;

    public static String subscribeUsersSuccessful;

    public static String subscribeUsersAlreadyFriends;

    public static String subscribeUsersAlreadySubscribed;

    public static String blockingUserIsAlreadyBlocked;

    public static String blockingUserIsSuccessful;

    public static String emailListOfUserIsSuccessful;

    @Value("${create.users.invalid}")
    public void setCreateRequestInvalid(String createRequestInvalid) {
        Messages.createRequestInvalid = createRequestInvalid;
    }

    @Value("${create.users.email.count}")
    public void setCreateRequest2EmailAllowed(String createRequest2EmailAllowed) {
        Messages.createRequest2EmailAllowed = createRequest2EmailAllowed;
    }

    @Value("${create.users.email.invalid}")
    public void setCreateRequestInvalidEmailIds(String createRequestInvalidEmailIds) {
        Messages.createRequestInvalidEmailIds = createRequestInvalidEmailIds;
    }

    @Value("${create.users.email.are.same}")
    public void setCreateRequestEmailIdsAreSame(String createRequestEmailIdsAreSame) {
        Messages.createRequestEmailIdsAreSame = createRequestEmailIdsAreSame;
    }

    @Value("${create.friends.already.hasconnection}")
    public void setCreateFriendsAlreadyHasConnection(String createFriendsAlreadyHasConnection) {
        Messages.createFriendsAlreadyHasConnection = createFriendsAlreadyHasConnection;
    }

    @Value("${create.friends.connection.success}")
    public void setCreateFriendsSuccessful(String createFriendsSuccessful) {
        Messages.createFriendsSuccessful = createFriendsSuccessful;
    }

    @Value("${friends.list.users.isnot.available}")
    public void setFriendslistUserNotAvailable(String friendslistUserNotAvailable) {
        Messages.friendslistUserNotAvailable = friendslistUserNotAvailable;
    }

    @Value("${friends.list.users.success}")
    public void setFriendslistUserSuccessful(String friendslistUserSuccessful) {
        Messages.friendslistUserSuccessful = friendslistUserSuccessful;
    }

    @Value("${common.friends.users.isnot.available}")
    public void setCommonFriendsUserNotAvailable(String commonFriendsUserNotAvailable) {
        Messages.commonFriendsUserNotAvailable = commonFriendsUserNotAvailable;
    }

    @Value("${common.friends.users.empty.list}")
    public void setCommonFriendsEmptyList(String commonFriendsEmptyList) {
        Messages.commonFriendsEmptyList = commonFriendsEmptyList;
    }

    @Value("${subscribe.friends.requestor.target.notvalid.user}")
    public void setSubscribeUsersRequestorTargetNotValidUser(String subscribeUsersRequestorTargetNotValidUser) {
        Messages.subscribeUsersRequestorTargetNotValidUser = subscribeUsersRequestorTargetNotValidUser;
    }

    @Value("${subscribe.friends.successful}")
    public void setSubscribeUsersSuccessful(String subscribeUsersSuccessful) {
        Messages.subscribeUsersSuccessful = subscribeUsersSuccessful;
    }

    @Value("${subscribe.friends.already.friends}")
    public void setSubscribeUsersAlreadyFriends(String subscribeUsersAlreadyFriends) {
        Messages.subscribeUsersAlreadyFriends = subscribeUsersAlreadyFriends;
    }

    @Value("${subscribe.friends.already.subscribed}")
    public void setSubscribeUsersAlreadySubscribed(String subscribeUsersAlreadySubscribed) {
        Messages.subscribeUsersAlreadySubscribed = subscribeUsersAlreadySubscribed;
    }

    @Value("${blocking.user.is.already.blocked}")
    public void setBlockingUserIsAlreadyBlocked(String blockingUserIsAlreadyBlocked) {
        Messages.blockingUserIsAlreadyBlocked = blockingUserIsAlreadyBlocked;
    }

    @Value("${blocking.user.is.successful}")
    public void setBlockingUserIsSuccessful(String blockingUserIsSuccessful) {
        Messages.blockingUserIsSuccessful = blockingUserIsSuccessful;
    }

    @Value("${update.email.list.successful}")
    public void setEmailListOfUserIsSuccessful(String emailListOfUserIsSuccessful) {
        Messages.emailListOfUserIsSuccessful = emailListOfUserIsSuccessful;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
