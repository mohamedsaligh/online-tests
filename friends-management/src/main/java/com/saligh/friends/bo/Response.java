package com.saligh.friends.bo;

import com.owlike.genson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by saligh on 19/2/18.
 */
@Setter
@Getter
@ToString
public class Response {

    private boolean success;

    @JsonIgnore(serialize = true)
    private List<String> friends;

    private int count;

    @JsonIgnore(serialize = true)
    private String errormsg;
}
