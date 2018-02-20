package com.saligh.friends.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by saligh on 21/2/18.
 */
@Getter
@Setter
@Entity
@Table(name = "UPDATES")
public class Updates implements Serializable {

    @Id
    @Column(name = "UPDATE_ID")
    private String updateId;

    @Column(name = "UPDATE")
    private String update;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "CREATED_DT")
    private Timestamp createdDt;

    @Column(name = "IPADDRESS")
    private String ipAddress;

}
