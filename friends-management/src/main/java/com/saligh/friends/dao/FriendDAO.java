package com.saligh.friends.dao;

import com.saligh.friends.bo.CreateRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by saligh on 19/2/18.
 */
@Slf4j
@Getter
@Setter
public class FriendDAO {

    @PersistenceContext(name = "friendsem")
    EntityManager entityManager;

    @Transactional
    public void createFriend(CreateRequest createRequest) {
        log.info("Object to persist: " + createRequest);
        //entityManager.persist(createRequest);
    }

}
