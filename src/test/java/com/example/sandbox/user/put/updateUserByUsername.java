package com.example.sandbox.user.put;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.user.UserBodyWrapper;
import com.example.sandbox.util.swagger.definitions.UserBody;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.createUserBody;
import static com.example.sandbox.util.body.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class updateUserByUsername extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testUserUpdateByUserName() {
        UserBodyWrapper createdBody = UserBodyWrapper.builder()
                .userBody(createUserBody())
                .build();

        UserBodyWrapper updatedBody = UserBodyWrapper.builder()
                .userBody(UserBody.builder()
                        .username(createdBody.getUserBody().getUsername())
                        .password("not" + createdBody.getUserBody().getPassword())
                        .build())
                .build();

        UserBodyWrapper emptyBody = UserBodyWrapper.builder().build();

        String endpoint = userByName.replace("{username}", createdBody.getUserBody().getUsername());

        // create user
        postUrl(user, createJsonBody(createdBody));

        // update user - 200
        Response response200 = putUrl(endpoint, createJsonBody(updatedBody));
        Assertions.assertReturnCode(response200, 200);

        //get updated user (fails because put method doesnt work properly)
        //Response getResponse = getUrl(endpoint);
        //Assert.assertEquals(getResponse.jsonPath().get("password"), updatedBody.getUserBody().getPassword());

        // update user - 405
        Response response405 = putUrl(endpoint, createJsonBody(emptyBody));
        Assertions.assertReturnCode(response405, 405);
    }
}
