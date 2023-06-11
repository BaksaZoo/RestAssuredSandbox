package com.example.sandbox.util;

import io.restassured.response.Response;
import org.junit.Assert;

public class Assertions {
    public static void assertReturnCode(Response response,Integer code){
        Assert.assertEquals((Integer) response.getStatusCode(),code);
    }
}
