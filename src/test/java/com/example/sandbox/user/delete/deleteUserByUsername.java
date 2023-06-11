package com.example.sandbox.user.delete;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.user.UserBodyWrapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.createUserBody;
import static com.example.sandbox.util.body.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class deleteUserByUsername extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testUserDeleteByUserName() {
        UserBodyWrapper body = UserBodyWrapper.builder()
                .userBody(createUserBody())
                .build();

        String endpoint = userByName.replace("{username}", body.getUserBody().getUsername());

        // create user
        postUrl(user, createJsonBody(body));

        // delete user - 200 and 404 (possible to have multiple users with the same username so the second delete request can return 200 too)
        //for (int i = 0; i < 2; i++) {
        //    Response response = deleteUrl(endpoint);
        //    Assertions.assertReturnCode(response, i < 1 ? 200 : 404);
        //}

        Response response = deleteUrl(endpoint);
        Assertions.assertReturnCode(response, 200);
    }
}
