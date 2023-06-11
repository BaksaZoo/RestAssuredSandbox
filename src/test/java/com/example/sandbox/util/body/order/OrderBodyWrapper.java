package com.example.sandbox.util.body.order;

import com.example.sandbox.util.body.BodyWrapper;
import com.example.sandbox.util.body.JsonBody;
import com.example.sandbox.util.swagger.definitions.OrderBody;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderBodyWrapper extends JsonBody implements BodyWrapper {

    @JsonProperty
    private OrderBody orderBody;


    @Override
    public Object getBody() {
        return orderBody;
    }
}
