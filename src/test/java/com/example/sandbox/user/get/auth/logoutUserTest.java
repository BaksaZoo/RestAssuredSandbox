package com.example.sandbox.user.get.auth;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.user.UserBodyWrapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.createUserBody;
import static com.example.sandbox.util.body.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class logoutUserTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testUserLogout() {
        UserBodyWrapper body = UserBodyWrapper.builder()
                .userBody(createUserBody())
                .build();

        String loginEndpoint = login + "?username=" + body.getUserBody().getUsername() +
                "&password=" + body.getUserBody().getPassword();

        // create user
        postUrl(user, createJsonBody(body));

        // login user
        getUrl(loginEndpoint);

        // logout user
        Response response = getUrl(logout);
        Assertions.assertReturnCode(response, 200);
    }
}
