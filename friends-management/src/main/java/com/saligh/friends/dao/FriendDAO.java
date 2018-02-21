package com.saligh.friends.dao;

import com.mysema.query.hql.HQLQuery;
import com.mysema.query.hql.HQLSubQuery;
import com.mysema.query.hql.jpa.JPAQuery;
import com.saligh.friends.db.Friends;
import com.saligh.friends.db.QFriends;
import com.saligh.friends.db.QUsers;
import com.saligh.friends.db.Users;
import com.saligh.friends.utils.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by saligh on 19/2/18.
 */
@Slf4j
@Getter
@Setter
public class FriendDAO {

    @PersistenceContext(name = "friendsem")
    EntityManager entityManager;

    public Users getUsers(String email) {
        QUsers users = QUsers.users;
        HQLQuery query = new JPAQuery(entityManager);
        return query.from(users).where(users.username.eq(email)).uniqueResult(users);
    }

    public void createUsers(Users users) {
        entityManager.persist(users);
    }

    public void updateUsers(Users users) {
        entityManager.merge(users);
    }

    public boolean checkIfFriends(String email1, String email2) {
        QFriends friends = QFriends.friends;
        HQLQuery query = new JPAQuery(entityManager);
        long count = query.from(friends).where(friends.friendsPK.friendOne.eq(email1)
                .and(friends.friendsPK.friendTwo.eq(email2)))
                .count();

        return count < 1;
    }

    public void saveFriends(List<Friends> friends, String status) {
        for(Friends friend: friends) {
            friend.setStatus(status);
            createFriends(friend);
        }
    }

    public void createFriends(Friends friends) {
        entityManager.persist(friends);
    }

    public void updateFriends(Friends friends) {
        entityManager.merge(friends);
    }

    public List<String> getFriendsList(String email) {
        QFriends friends = QFriends.friends;
        HQLQuery query = new JPAQuery(entityManager);
        return query.from(friends).where(friends.friendsPK.friendOne.eq(email)
                .and(friends.status.eq(Status.FRIEND.getStatus())))
                .list(friends.friendsPK.friendTwo);
    }

    public List<String> getCommonFriends(String email1, String email2) {
        QFriends friend1 = new QFriends("friend1");
        QFriends friend2 = new QFriends("friend2");

        HQLQuery query = new JPAQuery(entityManager);

        return query.from(friend1).where(friend1.friendsPK.friendOne.eq(email1).and(friend1.status.eq(Status.FRIEND.getStatus())).and(friend1.friendsPK.friendTwo.in(
                new HQLSubQuery().from(friend2).where(friend2.friendsPK.friendOne.eq(email2).and(friend2.status.eq(Status.FRIEND.getStatus()))).list(friend2.friendsPK.friendTwo))))
                .list(friend1.friendsPK.friendTwo);

    }

    public boolean checkIfAlreadyFriends(String requestor, String target) {
        QFriends friends = QFriends.friends;
        HQLQuery query = new JPAQuery(entityManager);

        long fCount = query.from(friends).where(friends.friendsPK.friendOne.eq(requestor).and(friends.friendsPK.friendTwo.eq(target))
                .and(friends.status.eq(Status.FRIEND.getStatus()))).count();

        return fCount > 0;
    }

    public boolean checkIfAlreadySubscribed(String requestor, String target) {
        QFriends friends = QFriends.friends;
        HQLQuery query = new JPAQuery(entityManager);

        long fCount = query.from(friends).where(friends.friendsPK.friendOne.eq(requestor).and(friends.friendsPK.friendTwo.eq(target))
                .and(friends.status.eq(Status.SUBSCRIBED.getStatus()))).count();

        return fCount > 0;
    }

    public Friends selectForBlocking(String requestor, String target) {
        QFriends friends = QFriends.friends;
        HQLQuery query = new JPAQuery(entityManager);

        return query.from(friends).where(friends.friendsPK.friendOne.eq(requestor).and(friends.friendsPK.friendTwo.eq(target))).uniqueResult(friends);
    }

    public List<String> getAllFriendsSubscribers(String email) {
        QFriends friends = QFriends.friends;
        HQLQuery query = new JPAQuery(entityManager);

        return query.from(friends).where(friends.friendsPK.friendOne.eq(email)
                    .and(friends.status.in(Status.FRIEND.getStatus(),Status.SUBSCRIBED.getStatus()))
                    .and(friends.status.ne(Status.BLOCKED.getStatus()))).list(friends.friendsPK.friendTwo);
    }

    public List<String> getAllUsersExceptBlocked(String email) {
        QFriends friends = QFriends.friends;
        QUsers users = QUsers.users;

        HQLQuery query = new JPAQuery(entityManager);

        return query.from(users).where(users.username.ne(email).and(users.username.notIn(new HQLSubQuery().from(friends).where(friends.friendsPK.friendOne.eq(email)
                        .and(friends.status.in(Status.FRIEND.getStatus(),Status.SUBSCRIBED.getStatus(),Status.BLOCKED.getStatus())))
                        .list(friends.friendsPK.friendTwo))))
                        .list(users.username);
    }
}
