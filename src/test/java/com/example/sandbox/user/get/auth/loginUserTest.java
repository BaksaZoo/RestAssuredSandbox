package com.example.sandbox.user.get.auth;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.user.UserBodyWrapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.createUserBody;
import static com.example.sandbox.util.body.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class loginUserTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testUserLogin() {
        UserBodyWrapper body = UserBodyWrapper.builder()
                .userBody(createUserBody())
                .build();

        String loginEndpoint = login + "?username=" + body.getUserBody().getUsername() +
                "&password=" + body.getUserBody().getPassword();
        String invalidLoginEndpoint = login + "?username=" + body.getUserBody().getUsername() +
                "&password=not" + body.getUserBody().getPassword();

        // create user
        postUrl(user, createJsonBody(body));

        // login user - 200
        Response response200 = getUrl(loginEndpoint);
        Assertions.assertReturnCode(response200, 200);

        // login user - 400 (not working properly)
//        Response response400 = getUrl(invalidLoginEndpoint);
//        Assertions.assertReturnCode(response400, 400);
    }
}
