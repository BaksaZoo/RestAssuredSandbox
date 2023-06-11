package com.example.sandbox.util.body.pet;

import com.example.sandbox.util.body.BodyWrapper;
import com.example.sandbox.util.body.JsonBody;
import com.example.sandbox.util.swagger.definitions.PetBody;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PetBodyWrapper extends JsonBody implements BodyWrapper {

        @JsonProperty
        private PetBody petBody;

        @Override
        public Object getBody() {
                return petBody;
        }
}
