package com.saligh.friends.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by saligh on 20/2/18.
 */
@Getter
@Setter
@Entity
@Table(name = "USERS")
@NoArgsConstructor
public class Users implements Serializable {

    @Id
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FRIEND_COUNT")
    private Integer friendCount;

    @Transient
    private boolean isNew;

}
