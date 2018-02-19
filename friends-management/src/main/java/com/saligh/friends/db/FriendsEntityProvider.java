package com.saligh.friends.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by saligh on 18/2/18.
 */
@Getter
@Setter
public class FriendsEntityProvider {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        if (entityManagerFactory.isOpen()) {
            this.entityManager = entityManagerFactory.createEntityManager();
        }
    }

}
