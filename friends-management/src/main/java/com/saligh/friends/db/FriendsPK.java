package com.saligh.friends.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by saligh on 20/2/18.
 */
@Getter
@Setter
@Embeddable
public class FriendsPK implements Serializable {

    @Column(name = "FRIEND_ONE")
    private String friendOne;

    @Column(name = "FRIEND_TWO")
    private String friendTwo;
}
