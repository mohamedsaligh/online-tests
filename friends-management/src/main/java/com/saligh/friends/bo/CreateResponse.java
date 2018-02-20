package com.saligh.friends.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by saligh on 20/2/18.
 */
@Setter
@Getter
@ToString
public class CreateResponse {

    private boolean success;

    private String message;

    public CreateResponse(boolean status, String message) {
        this.success = status;
        this.message = message;
    }
}
