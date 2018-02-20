package com.saligh.friends.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by saligh on 21/2/18.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class UpdateResponse {

    private boolean success;

    private List<String> recipients;

    private String message;
}
