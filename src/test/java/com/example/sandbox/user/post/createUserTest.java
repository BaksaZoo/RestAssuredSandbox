package com.example.sandbox.user.post;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.user.UserBodyWrapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.createUserBody;
import static com.example.sandbox.util.body.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class createUserTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testCreateUser() {
        UserBodyWrapper body = UserBodyWrapper.builder()
                .userBody(createUserBody())
                .build();
        UserBodyWrapper emptyBody = UserBodyWrapper.builder().build();

        // create - 200
        Response response200 = postUrl(user, createJsonBody(body));
        Assertions.assertReturnCode(response200, 200);

        // create 405
        Response response405 = postUrl(user, createJsonBody(emptyBody));
        Assertions.assertReturnCode(response405, 405);
    }
}
