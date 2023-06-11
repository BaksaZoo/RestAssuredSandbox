package com.example.sandbox.util.body;


import com.example.sandbox.util.body.BodyWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class JsonBody {

    public static String createJsonBody(BodyWrapper bodyWrapper){
        try{
            return new ObjectMapper().writeValueAsString(bodyWrapper.getBody());

        } catch (Throwable e){
            throw new RuntimeException("Body Generation Failure");
        }
    }

}
