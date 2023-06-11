package com.example.sandbox.user.post;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.BodyWrapper;
import com.example.sandbox.util.body.user.UserBodyWrapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static com.example.sandbox.util.Tools.createUserBody;
import static com.example.sandbox.util.body.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class createUserWithListTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testCreateUserWithList() {
        UserBodyWrapper body1 = UserBodyWrapper.builder()
                .userBody(createUserBody())
                .build();
        UserBodyWrapper body2 = UserBodyWrapper.builder()
                .userBody(createUserBody())
                .build();
        List<BodyWrapper> users = List.of(body1, body2);
        UserBodyWrapper emptyBody = UserBodyWrapper.builder().build();

        // create - 200
        Response response200 = postUrl(createWithList, createJsonBody(users));
        Assertions.assertReturnCode(response200, 200);

        // create - 405
        Response response405 = postUrl(createWithList, createJsonBody(emptyBody));
        Assertions.assertReturnCode(response405, 405);
    }
}
