package com.example.sandbox.util.body.user;

import com.example.sandbox.util.body.BodyWrapper;
import com.example.sandbox.util.body.JsonBody;
import com.example.sandbox.util.swagger.definitions.UserBody;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBodyWrapper extends JsonBody implements BodyWrapper {

    @JsonProperty
    private UserBody userBody;

    @Override
    public Object getBody() {
        return userBody;
    }
}
