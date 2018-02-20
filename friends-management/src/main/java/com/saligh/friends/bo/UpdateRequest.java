package com.saligh.friends.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by saligh on 21/2/18.
 */
@Getter
@Setter
@ToString
public class UpdateRequest {

    private String sender;

    private String text;
}
