package com.saligh.friends.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by saligh on 18/2/18.
 */
@Getter
@Setter
@ToString
public class CreateRequest {

    private List<String> friends;

}
