package com.saligh.friends.utils;

import lombok.Getter;

/**
 * Created by saligh on 21/2/18.
 */
@Getter
public enum Status {

    SELF("0", "Self"), //future
    FRIEND("1", "Friend"),
    SUBSCRIBED("2", "Subscribed"),
    BLOCKED("3", "Blocked");


    private final String status;
    private final String description;

    private Status(String stat, String desc) {
        status = stat;
        description = desc;
    }

    public boolean equalsStatus(String otherStatus) {
        return status.equals(otherStatus);
    }

    public boolean equalsDescription(String otherDesc) {
        return description.equals(otherDesc);
    }
}
