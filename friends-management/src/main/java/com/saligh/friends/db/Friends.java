package com.saligh.friends.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by saligh on 20/2/18.
 */
@Getter
@Setter
@Entity
@Table(name = "FRIENDS")
public class Friends implements Serializable {

    @EmbeddedId
    private FriendsPK friendsPK;

    @Column(name = "STATUS")
    private String status;

}
