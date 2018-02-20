package com.saligh.friends.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by saligh on 21/2/18.
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
public class UserResponse {

    private boolean success;

    private List<String> friends;

    private int count;

    private String message;

}
