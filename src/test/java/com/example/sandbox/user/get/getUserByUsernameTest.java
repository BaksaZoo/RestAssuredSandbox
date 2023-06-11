package com.example.sandbox.user.get;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.user.UserBodyWrapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.createUserBody;
import static com.example.sandbox.util.body.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class getUserByUsernameTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testGetUserByUsername() {
        UserBodyWrapper body = UserBodyWrapper.builder()
                .userBody(createUserBody())
                .build();

        String endpoint = userByName.replace("{username}", String.valueOf(body.getUserBody().getUsername()));

        // create user
        postUrl(user, createJsonBody(body));

        // get user - 200
        Response response200 = getUrl(endpoint);
        Assertions.assertReturnCode(response200, 200);

        // delete user
        deleteUrl(endpoint);

        // get user - 404
        Response response404 = getUrl(endpoint);
        Assertions.assertReturnCode(response404, 404);
    }
}
